package products.burgers

import products.Product

@kotlinx.serialization.Serializable
data class Burger(
    override val price: Int,
    val burgerType: BurgerType,
    var size: BurgerSize,
) : Product {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}