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
import products.burgers.Burger
import products.burgers.BurgerSerialization
import java.util.*
import java.util.logging.Logger


class AddBurger {

    private var cosmosClient = CosmosClientBuilder()
        .endpoint("https://food-order-picker-db.documents.azure.com:443/")
        .key("SkcGhJ0IzZQOv6iYCwkjAMuYvWXZN8YmOiavkO2dgrhp51hjScLfJXw2LBhhLFk6sd49DlNiWYuEJUxiGhbN2g==")
        .buildClient()

    private val databaseName = "Products"
    private val containerName = "Orders"

    private var database: CosmosDatabase? = null
    private var container: CosmosContainer? = null

    @FunctionName("AddBurger")
    fun addBurger(
        @HttpTrigger(name = "req",
            methods = [HttpMethod.POST],
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "burger"
        ) request: HttpRequestMessage<Optional<String?>>,
        context: ExecutionContext,
    ): HttpResponseMessage {
        context.logger.info("AddBurger HTTP trigger function invoked with post method.")

        val requestBody = request.body

        return if ((requestBody == null) || !requestBody.isPresent) {
            context.logger.info("Request body is empty.")
            request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Not a valid request").build()
        } else {

            context.logger.info("Request body: ${requestBody.get()}")
            val burger = BurgerSerialization().json.decodeFromString<Burger>(requestBody.get())
            context.logger.info("Deserialized burger from request body.")

            createDatabaseIfNotExists(context.logger)
            createContainerIfNotExists(context.logger)

            val cosmosItemRequestOptions = CosmosItemRequestOptions()
            context.logger.info("Created cosmos item request options.")

            val item: CosmosItemResponse<Burger> =
                container!!.createItem(burger, PartitionKey(burger.id), cosmosItemRequestOptions)

            context.logger.info("Created item with request charge of ${item.requestCharge} within duration ${item.duration}");

            cosmosClient.close()
            context.logger.info("Close connection with CosmosDB.")

            context.logger.info("Created new burger.")
            request.createResponseBuilder(HttpStatus.OK).body("Okay").build()
        }
    }

    @FunctionName("GetBurger")
    fun getBurger(
        @HttpTrigger(name = "req",
            methods = [HttpMethod.GET],
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "burger"
        ) request: HttpRequestMessage<Optional<String?>>,
        context: ExecutionContext,
    ): HttpResponseMessage {
        context.logger.info("GetBurger HTTP trigger function invoked with get method.")

        val id = request.queryParameters["id"]

        if (id != null) {
            try {

                createDatabaseIfNotExists(context.logger)
                createContainerIfNotExists(context.logger)

                if (container != null) {
                    val item: CosmosItemResponse<Burger> = container!!.readItem(id.toString(), PartitionKey(id), Burger::class.java)
                    val requestCharge = item.requestCharge
                    val requestLatency: java.time.Duration? = item.duration
                    context.logger.info("Item successfully read with id ${item.item} with a charge of $requestCharge and within duration $requestLatency")

                    return request
                        .createResponseBuilder(HttpStatus.OK)
                        .body(item.item)
                        .build()
                } else {
                    context.logger.info("Read burger failed, because container with burger does not exist.")
                    return request
                        .createResponseBuilder(HttpStatus.BAD_REQUEST)
                        .body("Container with burger data does not exist.")
                        .build()
                }
            } catch (e: Exception) {
                context.logger.info("Read burger failed with $e.")
                return request
                    .createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Read burger failed.")
                    .build()
            }
        }
        context.logger.info("Read burger failed, there is no id value in query parameters.")
        return request
            .createResponseBuilder(HttpStatus.BAD_REQUEST)
            .body("Please pass a id on the query string")
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
