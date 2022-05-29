package products.burgers

import kotlinx.serialization.Serializable
import model.UUIDSerializer
import java.util.*

@kotlinx.serialization.Serializable
data class CheeseBurger(override val price: Int, override val size: BurgerSize, @Serializable(with = UUIDSerializer::class) override val id: UUID = UUID.randomUUID()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigTasty(override val price: Int, override val size: BurgerSize, @Serializable(with = UUIDSerializer::class) override val id: UUID = UUID.randomUUID()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class BigMack(override val price: Int, override val size: BurgerSize, @Serializable(with = UUIDSerializer::class) override val id: UUID = UUID.randomUUID()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Hamburger(override val price: Int, override val size: BurgerSize, @Serializable(with = UUIDSerializer::class) override val id: UUID = UUID.randomUUID()) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}