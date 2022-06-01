package products.burgers

import com.fasterxml.jackson.annotation.JsonProperty

@kotlinx.serialization.Serializable
data class CheeseBurger(@JsonProperty("price") override val price: Int, @JsonProperty("size") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigTasty(@JsonProperty("price") override val price: Int, @JsonProperty("size") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigMack(@JsonProperty("price") override val price: Int, @JsonProperty("size") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Hamburger(@JsonProperty("price") override val price: Int, @JsonProperty("size") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}