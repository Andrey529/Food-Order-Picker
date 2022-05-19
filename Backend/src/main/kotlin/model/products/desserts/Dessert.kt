package model.products.desserts

import model.products.Product

interface Dessert : Product {
    val filling: Filling
}