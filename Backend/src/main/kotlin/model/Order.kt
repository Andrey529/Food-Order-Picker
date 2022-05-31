package model

import kotlinx.serialization.Serializable
import products.burgers.Burger
import products.desserts.Dessert
import products.drinks.Drink

@Serializable
data class Order(
    var id: String?,
    val burgers: MutableList<Burger> = mutableListOf(),
    val drinks: MutableList<Drink> = mutableListOf(),
    val desserts: MutableList<Dessert> = mutableListOf()
)