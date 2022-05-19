package model.products.drinks

import model.products.Product

interface Drink : Product {
    val volume: Volume
}