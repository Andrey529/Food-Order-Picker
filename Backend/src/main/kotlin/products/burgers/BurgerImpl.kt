package products.burgers

import java.util.*

@kotlinx.serialization.Serializable
data class CheeseBurger(override val price: Int, override val size: BurgerSize, override val id: String = UUID.randomUUID().toString()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigTasty(override val price: Int, override val size: BurgerSize, override val id: String = UUID.randomUUID().toString()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigMack(override val price: Int, override val size: BurgerSize, override val id: String = UUID.randomUUID().toString()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Hamburger(override val price: Int, override val size: BurgerSize, override val id: String = UUID.randomUUID().toString()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}