package controllers

import com.azure.cosmos.CosmosClientBuilder
import com.azure.cosmos.CosmosContainer
import com.azure.cosmos.CosmosDatabase
import com.azure.cosmos.models.*
import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.AuthorizationLevel
import com.microsoft.azure.functions.annotation.FunctionName
import com.microsoft.azure.functions.annotation.HttpTrigger
import products.burgers.Burger
import products.burgers.BurgerSize
import products.burgers.CheeseBurger
import java.util.*
import java.util.logging.Logger


class AddBurger {

    private var cosmosClient = CosmosClientBuilder()
        .endpoint("https://food-order-picker-db.documents.azure.com:443/")
        .key("SkcGhJ0IzZQOv6iYCwkjAMuYvWXZN8YmOiavkO2dgrhp51hjScLfJXw2LBhhLFk6sd49DlNiWYuEJUxiGhbN2g==")
        .buildClient()

    private val databaseName = "ToDoList"
    private val containerName = "Items"

    private var database: CosmosDatabase? = null
    private var container: CosmosContainer? = null

    @FunctionName("AddBurger")
    fun run(
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

//            TODO("Deserialize json with burger and add it to CosmosDB")

            createDatabaseIfNotExists(context.logger)
            createContainerIfNotExists(context.logger)

            val cosmosItemRequestOptions = CosmosItemRequestOptions()
            val item: CosmosItemResponse<Burger> =
                container!!.createItem(CheeseBurger(50, BurgerSize.SINGLE), PartitionKey(UUID.randomUUID()), cosmosItemRequestOptions)

            context.logger.info("Created item with request charge of ${item.requestCharge} within duration ${item.duration}");

            cosmosClient.close()

            context.logger.info("Created new burger.")
            request.createResponseBuilder(HttpStatus.OK).body("Okay").build()
        }
    }

    @Throws(Exception::class)
    private fun createDatabaseIfNotExists(logger: Logger) {
        logger.info("Create database $databaseName if not exists.")

        //  Create database if not exists
        //  <CreateDatabaseIfNotExists>
        val cosmosDatabaseResponse: CosmosDatabaseResponse = cosmosClient.createDatabaseIfNotExists(databaseName)
        database = cosmosClient.getDatabase(cosmosDatabaseResponse.properties.id)
        //  </CreateDatabaseIfNotExists>

        logger.info("Checking database ${database!!.id} completed!\n")
    }

    @Throws(Exception::class)
    private fun createContainerIfNotExists(logger: Logger) {
        logger.info("Create container $containerName if not exists.")

        //  Create container if not exists
        //  <CreateContainerIfNotExists>
        val containerProperties = CosmosContainerProperties(containerName, "/lastName")

        //  Create container with 400 RU/s
        val cosmosContainerResponse: CosmosContainerResponse =
            database!!.createContainerIfNotExists(containerProperties, ThroughputProperties.createManualThroughput(400))
        container = database!!.getContainer(cosmosContainerResponse.properties.id)
        //  </CreateContainerIfNotExists>
        logger.info("Checking container ${container!!.id} completed!\n")
    }

}
