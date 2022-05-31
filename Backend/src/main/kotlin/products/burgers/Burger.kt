package products.burgers

import products.Product

interface Burger : Product {
    val size: BurgerSize
}