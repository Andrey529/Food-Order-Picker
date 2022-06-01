package products.desserts

import products.Product

@kotlinx.serialization.Serializable
data class Dessert(
    override val price: Int,
    val dessertType: DessertType,
    val filling: Filling,
) : Product {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}