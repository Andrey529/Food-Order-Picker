package products.drinks

@kotlinx.serialization.Serializable
data class Water(override val price: Int, override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Cola(override val price: Int, override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Pepsi(override val price: Int, override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Fanta(override val price: Int, override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}