package products.burgers

import com.fasterxml.jackson.annotation.JsonProperty
import products.Product

@kotlinx.serialization.Serializable
data class Burger(
    @JsonProperty("price") override val price: Int,
    @JsonProperty("burgerType") val burgerType: BurgerType,
    @JsonProperty("size") val size: BurgerSize,
) : Product {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}