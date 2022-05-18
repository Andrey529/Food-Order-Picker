package model.products.desserts

data class Pie(override val price: Int, override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

data class Pancake(override val price: Int, override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

data class Donut(override val price: Int, override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}