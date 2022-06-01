package products.drinks

import products.Product

@kotlinx.serialization.Serializable
data class Drink(
    override val price: Int,
    val drinkType: DrinkType,
    val volume: Volume,
) : Product {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}