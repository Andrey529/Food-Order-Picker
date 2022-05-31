package model

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import products.burgers.Burger
import products.desserts.Dessert
import products.drinks.Drink

@Serializable
data class Order(
    @JsonProperty("id") var id: String?,
    @JsonProperty("burgers") val burgers: MutableList<Burger> = mutableListOf(),
    @JsonProperty("drinks") val drinks: MutableList<Drink> = mutableListOf(),
    @JsonProperty("desserts") val desserts: MutableList<Dessert> = mutableListOf()
)