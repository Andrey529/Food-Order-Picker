package products.drinks

import com.fasterxml.jackson.annotation.JsonProperty
import products.Product

@kotlinx.serialization.Serializable
data class Drink(
    @JsonProperty("price") override val price: Int,
    @JsonProperty("drinkType") val drinkType: DrinkType,
    @JsonProperty("volume") val volume: Volume,
) : Product {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}