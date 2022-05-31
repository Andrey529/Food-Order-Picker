package products.burgers

import com.fasterxml.jackson.annotation.JsonProperty

@kotlinx.serialization.Serializable
data class CheeseBurger(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigTasty(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigMack(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Hamburger(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}