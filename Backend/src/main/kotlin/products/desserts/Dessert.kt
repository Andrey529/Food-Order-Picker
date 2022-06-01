package products.desserts

import com.fasterxml.jackson.annotation.JsonProperty
import products.Product

@kotlinx.serialization.Serializable
data class Dessert(
    @JsonProperty("price") override val price: Int,
    @JsonProperty("dessertType") val dessertType: DessertType,
    @JsonProperty("filling") val filling: Filling,
) : Product {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}