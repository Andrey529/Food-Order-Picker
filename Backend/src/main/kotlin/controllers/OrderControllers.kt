package controllers

import com.azure.cosmos.CosmosClientBuilder
import com.azure.cosmos.CosmosContainer
import com.azure.cosmos.CosmosDatabase
import com.azure.cosmos.models.*
import com.azure.cosmos.util.CosmosPagedIterable
import com.azure.identity.DefaultAzureCredentialBuilder
import com.azure.security.keyvault.secrets.SecretClientBuilder
import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.AuthorizationLevel
import com.microsoft.azure.functions.annotation.FunctionName
import com.microsoft.azure.functions.annotation.HttpTrigger
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import model.Order
import products.serializer.ProductSerialization
import java.util.*
import java.util.logging.Logger


class OrderControllers {

    private var secretClient = SecretClientBuilder()
        .vaultUrl("https://order-picker-key-vault.vault.azure.net/")
        .credential(DefaultAzureCredentialBuilder().build())
        .buildClient()

    var secret = secretClient.setSecret("da", "da")


    private var cosmosClientEndpoint = secretClient.getSecret("food-order-picker-db-url")
    private var cosmosClientKey = secretClient.getSecret("food-order-picker-db-key")

    private var cosmosClient = CosmosClientBuilder()
        .endpoint(cosmosClientEndpoint.value)
        .key(cosmosClientKey.value)
        .buildClient()

    private val databaseName = secretClient.getSecret("food-order-picker-db-databaseName").value
    private val containerName = secretClient.getSecret("food-order-picker-db-containerName").value
    private val partionKeyPath = "/id"

    private var database: CosmosDatabase? = null
    private var container: CosmosContainer? = null

    private val json = ProductSerialization().json

    // POST: api/order
    // body: <Json with order>
    @FunctionName("AddOrder")
    fun addOrder(
        @HttpTrigger(name = "req",
            methods = [HttpMethod.POST],
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "order"
        ) request: HttpRequestMessage<Optional<String?>>,
        context: ExecutionContext,
    ): HttpResponseMessage {
        context.logger.info("AddOrder HTTP trigger function invoked with post method.")

        val requestBody = request.body

        return if ((requestBody == null) || !requestBody.isPresent) {
            context.logger.info("Request body is empty.")
            request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Not a valid request").build()
        } else {

            context.logger.info("Request body: ${requestBody.get()}")
            val order = json.decodeFromString<Order>(requestBody.get())
            context.logger.info("Deserialized order from request body.")

            val id = UUID.randomUUID().toString()
            order.id = id

            context.logger.info("Request body with id: ${json.encodeToString(order)}")

            createDatabaseIfNotExists(context.logger)
            createContainerIfNotExists(context.logger)

            val cosmosItemRequestOptions = CosmosItemRequestOptions()
            context.logger.info("Created cosmos item request options.")

            val item: CosmosItemResponse<Order> =
                container!!.createItem(order, PartitionKey(id), cosmosItemRequestOptions)

            context.logger.info("Created item with request charge of ${item.requestCharge} within duration ${item.duration}");

            cosmosClient.close()
            context.logger.info("Close connection with CosmosDB.")

            context.logger.info("Created new order.")
            request.createResponseBuilder(HttpStatus.OK).body(id).build()
        }
    }

    // GET: api/order?id=<orderId>
    @FunctionName("GetOrder")
    fun getOrder(
        @HttpTrigger(name = "req",
            methods = [HttpMethod.GET],
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "order"
        ) request: HttpRequestMessage<Optional<String?>>,
        context: ExecutionContext,
    ): HttpResponseMessage {
        context.logger.info("GetOrder HTTP trigger function invoked with get method.")

        val id = request.queryParameters["id"]

        if (id != null) {
            try {
                createDatabaseIfNotExists(context.logger)
                createContainerIfNotExists(context.logger)

                if (container != null) {
                    val item: CosmosItemResponse<Order> = container!!.readItem(id, PartitionKey(id), Order::class.java)
                    val requestCharge = item.requestCharge
                    val requestLatency: java.time.Duration? = item.duration
                    context.logger.info("Item successfully read with id ${item.item} with a charge of $requestCharge and within duration $requestLatency")

                    cosmosClient.close()
                    context.logger.info("Close connection with CosmosDB.")

                    return request
                        .createResponseBuilder(HttpStatus.OK)
                        .body(item.item)
                        .build()
                } else {
                    cosmosClient.close()
                    context.logger.info("Close connection with CosmosDB.")

                    context.logger.info("Read order failed, because container with order does not exist.")
                    return request
                        .createResponseBuilder(HttpStatus.BAD_REQUEST)
                        .body("Container with order data does not exist.")
                        .build()
                }
            } catch (e: Exception) {
                cosmosClient.close()
                context.logger.info("Close connection with CosmosDB.")

                context.logger.info("Read order failed with $e.")
                return request
                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Read order failed.")
                    .build()
            }
        }
        context.logger.info("Read order failed, there is no id value in query parameters.")
        return request
            .createResponseBuilder(HttpStatus.BAD_REQUEST)
            .body("Please pass an id on the query string")
            .build()
    }

    // GET: api/orders
    @FunctionName("GetOrders")
    fun getOrders(
        @HttpTrigger(name = "req",
            methods = [HttpMethod.GET],
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "orders"
        ) request: HttpRequestMessage<Optional<String?>>,
        context: ExecutionContext,
    ): HttpResponseMessage {
        context.logger.info("GetOrders HTTP trigger function invoked with get method.")

        try {
            createDatabaseIfNotExists(context.logger)
            createContainerIfNotExists(context.logger)

            if (container != null) {
                val items: CosmosPagedIterable<Order> = container!!.readAllItems(PartitionKey(""), Order::class.java)
                context.logger.info("Items successfully read")

                val listItems = mutableListOf<Order>()
                items.forEach {
                    listItems.add(it)
                }

                cosmosClient.close()
                context.logger.info("Close connection with CosmosDB.")

                return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body(json.encodeToString(listItems))
                    .build()
            } else {
                cosmosClient.close()
                context.logger.info("Close connection with CosmosDB.")

                context.logger.info("Read orders failed, because container with orders does not exist.")
                return request
                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Container with order data does not exist.")
                    .build()
            }
        } catch (e: Exception) {
            cosmosClient.close()
            context.logger.info("Close connection with CosmosDB.")

            context.logger.info("Read orders failed with $e.")
            return request
                .createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Read orders failed.")
                .build()
        }
    }

    // DELETE: api/order?id=<orderId>
    @FunctionName("DeleteOrder")
    fun DeleteOrder(
        @HttpTrigger(name = "req",
            methods = [HttpMethod.DELETE],
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "order"
        ) request: HttpRequestMessage<Optional<String?>>,
        context: ExecutionContext,
    ): HttpResponseMessage {
        context.logger.info("DeleteOrder HTTP trigger function invoked with delete method.")

        val id = request.queryParameters["id"]

        if (id != null) {
            try {
                createDatabaseIfNotExists(context.logger)
                createContainerIfNotExists(context.logger)

                return if (container != null) {
                    container!!.deleteItem(id.toString(), PartitionKey(id), CosmosItemRequestOptions())
                    context.logger.info("Item successfully deleted with id = $id")

                    cosmosClient.close()
                    context.logger.info("Close connection with CosmosDB.")

                    request
                        .createResponseBuilder(HttpStatus.OK)
                        .body(id)
                        .build()
                } else {
                    cosmosClient.close()
                    context.logger.info("Close connection with CosmosDB.")

                    context.logger.info("Delete order failed, because container with order does not exist.")
                    request
                        .createResponseBuilder(HttpStatus.BAD_REQUEST)
                        .body("Container with order data does not exist.")
                        .build()
                }
            } catch (e: Exception) {
                cosmosClient.close()
                context.logger.info("Close connection with CosmosDB.")

                context.logger.info("Delete order failed with $e.")
                return request
                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Delete order failed.")
                    .build()
            }
        }
        context.logger.info("Delete order failed, there is no id value in query parameters.")
        return request
            .createResponseBuilder(HttpStatus.BAD_REQUEST)
            .body("Please pass an id on the query string")
            .build()
    }





    @Throws(Exception::class)
    private fun createDatabaseIfNotExists(logger: Logger) {
        logger.info("Create database $databaseName if not exists.")

        val cosmosDatabaseResponse: CosmosDatabaseResponse = cosmosClient.createDatabaseIfNotExists(databaseName)
        database = cosmosClient.getDatabase(cosmosDatabaseResponse.properties.id)

        logger.info("Checking database ${database!!.id} completed!\n")
    }

    @Throws(Exception::class)
    private fun createContainerIfNotExists(logger: Logger) {
        logger.info("Create container $containerName if not exists.")

        val containerProperties = CosmosContainerProperties(containerName, partionKeyPath)

        val cosmosContainerResponse: CosmosContainerResponse =
            database!!.createContainerIfNotExists(containerProperties, ThroughputProperties.createManualThroughput(400))
        container = database!!.getContainer(cosmosContainerResponse.properties.id)

        logger.info("Checking container ${container!!.id} completed!\n")
    }

}