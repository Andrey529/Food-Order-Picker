package model.products.burgers

import model.products.Product

interface Burger : Product {
    val size: BurgerSize
}