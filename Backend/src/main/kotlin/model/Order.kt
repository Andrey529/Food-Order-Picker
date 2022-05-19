package model

import model.products.burgers.Burger
import model.products.desserts.Dessert
import model.products.drinks.Drink
import java.util.UUID
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    @Serializable(with = UUIDSerializer::class) val userId: UUID,
    @Serializable(with = UUIDSerializer::class) val orderId: UUID,
    val burgers: MutableList<Burger> = mutableListOf(),
    val drinks: MutableList<Drink> = mutableListOf(),
    val desserts: MutableList<Dessert> = mutableListOf()
)