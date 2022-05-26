package model

import kotlinx.serialization.Serializable
import products.burgers.Burger
import products.desserts.Dessert
import products.drinks.Drink
import java.util.*

@Serializable
data class Order(
    @Serializable(with = UUIDSerializer::class) val orderId: UUID,
    val burgers: MutableList<Burger> = mutableListOf(),
    val drinks: MutableList<Drink> = mutableListOf(),
    val desserts: MutableList<Dessert> = mutableListOf()
)