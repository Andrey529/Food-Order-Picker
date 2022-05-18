package model.products.burgers

data class CheeseBurger(override val price: Int, override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

data class BigTasty(override val price: Int, override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

data class BigMack(override val price: Int, override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

data class Hamburger(override val price: Int, override val size: BurgerSize) : Burger {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}