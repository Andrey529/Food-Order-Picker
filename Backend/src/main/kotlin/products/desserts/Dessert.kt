package products.desserts

import products.Product

interface Dessert : Product {
    val filling: Filling
}