package controllers

import com.microsoft.azure.functions.annotation.HttpTrigger
import com.microsoft.azure.functions.HttpMethod
import com.microsoft.azure.functions.annotation.AuthorizationLevel
import com.microsoft.azure.functions.HttpRequestMessage
import com.microsoft.azure.functions.ExecutionContext
import com.microsoft.azure.functions.HttpResponseMessage
import com.microsoft.azure.functions.HttpStatus
import com.microsoft.azure.functions.annotation.FunctionName
import java.util.*

class AddBurger {
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
        return if (requestBody == null) {
            context.logger.info("Request body is empty.")
            request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Not a valid request").build()
        } else {

//            TODO("Deserialize json with burger and add it to CosmosDB")

            context.logger.info("Created new burger.")
            request.createResponseBuilder(HttpStatus.OK).build()
        }
    }
}
