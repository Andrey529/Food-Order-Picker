package model.products.desserts

@kotlinx.serialization.Serializable
data class Pie(override val price: Int, override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Pancake(override val price: Int, override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Donut(override val price: Int, override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}