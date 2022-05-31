package controllers

import com.azure.cosmos.CosmosClientBuilder
import com.azure.cosmos.CosmosContainer
import com.azure.cosmos.CosmosDatabase
import com.azure.cosmos.models.*
import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.AuthorizationLevel
import com.microsoft.azure.functions.annotation.FunctionName
import com.microsoft.azure.functions.annotation.HttpTrigger
import kotlinx.serialization.decodeFromString
import model.Order
import products.serializer.ProductSerialization
import java.util.*
import java.util.logging.Logger

class OrderControllers {

    private var cosmosClient = CosmosClientBuilder()
        .endpoint("https://food-order-picker-db.documents.azure.com:443/")
        .key("SkcGhJ0IzZQOv6iYCwkjAMuYvWXZN8YmOiavkO2dgrhp51hjScLfJXw2LBhhLFk6sd49DlNiWYuEJUxiGhbN2g==")
        .buildClient()

    private val databaseName = "Products"
    private val containerName = "Orders"

    private var database: CosmosDatabase? = null
    private var container: CosmosContainer? = null

    private val json = ProductSerialization().json

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

            context.logger.info("Request body with id: ${requestBody.get()}")

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


    @FunctionName("GetOrder")
    fun getBurger(
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
            createDatabaseIfNotExists(context.logger)
            createContainerIfNotExists(context.logger)

            if (container != null) {
                val item: CosmosItemResponse<Order> = container!!.readItem(id, PartitionKey(id), Order::class.java)
                val requestCharge = item.requestCharge
                val requestLatency: java.time.Duration? = item.duration
                context.logger.info("Item successfully read with id ${item.item} with a charge of $requestCharge and within duration $requestLatency")

                return request
                    .createResponseBuilder(HttpStatus.OK)
                    .body(item.item)
                    .build()
            } else {
                context.logger.info("Read order failed, because container with order does not exist.")
                return request
                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Container with order data does not exist.")
                    .build()
            }

//            try {
//
//            } catch (e: Exception) {
//                context.logger.info("Read burger failed with $e.")
//                return request
//                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
//                    .body("Read burger failed.")
//                    .build()
//            }
        }
        context.logger.info("Read order failed, there is no id value in query parameters.")
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

        val containerProperties = CosmosContainerProperties(containerName, "/id")

        val cosmosContainerResponse: CosmosContainerResponse =
            database!!.createContainerIfNotExists(containerProperties, ThroughputProperties.createManualThroughput(400))
        container = database!!.getContainer(cosmosContainerResponse.properties.id)

        logger.info("Checking container ${container!!.id} completed!\n")
    }

}