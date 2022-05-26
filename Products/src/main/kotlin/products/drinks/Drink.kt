package products.drinks

import products.Product

interface Drink : Product {
    val volume: Volume
}