package products.serializer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import products.burgers.BigMack
import products.burgers.Burger
import products.burgers.BurgerSize
import products.burgers.CheeseBurger
import products.desserts.Dessert
import products.desserts.Donut
import products.desserts.Filling
import products.desserts.Pie
import products.drinks.Cola
import products.drinks.Drink
import products.drinks.Volume
import products.drinks.Water

internal class ProductSerializationTest {

    @Test
    fun encode() {
        val json = ProductSerialization().json
        val burgers = mutableListOf<Burger>(CheeseBurger(120, BurgerSize.DOUBLE), BigMack(150, BurgerSize.SINGLE))
        val drinks = mutableListOf<Drink>(Water(50, Volume.THIRD_OF_LITER), Cola(90, Volume.TWO_THIRDS_OF_LITER))
        val desserts = mutableListOf<Dessert>(Pie(75, Filling.VANILLA_CREAM), Donut(50, Filling.CHOCOLATE_CREAM))

        val expectedBurgersStr = "[{\"type\":\"products.burgers.CheeseBurger\",\"price\":120,\"size\":\"DOUBLE\"},{\"type\":\"products.burgers.BigMack\",\"price\":150,\"size\":\"SINGLE\"}]"
        val expectedDrinksStr = "[{\"type\":\"products.drinks.Water\",\"price\":50,\"volume\":\"THIRD_OF_LITER\"},{\"type\":\"products.drinks.Cola\",\"price\":90,\"volume\":\"TWO_THIRDS_OF_LITER\"}]"
        val expectedDesserts = "[{\"type\":\"products.desserts.Pie\",\"price\":75,\"filling\":\"VANILLA_CREAM\"},{\"type\":\"products.desserts.Donut\",\"price\":50,\"filling\":\"CHOCOLATE_CREAM\"}]"

        assertEquals(expectedBurgersStr, json.encodeToString(burgers))
        assertEquals(expectedDrinksStr, json.encodeToString(drinks))
        assertEquals(expectedDesserts, json.encodeToString(desserts))
    }

    @Test
    fun decode() {
        val json = ProductSerialization().json
        val burgers = mutableListOf<Burger>(CheeseBurger(120, BurgerSize.DOUBLE), BigMack(150, BurgerSize.SINGLE))
        val drinks = mutableListOf<Drink>(Water(50, Volume.THIRD_OF_LITER), Cola(90, Volume.TWO_THIRDS_OF_LITER))
        val desserts = mutableListOf<Dessert>(Pie(75, Filling.VANILLA_CREAM), Donut(50, Filling.CHOCOLATE_CREAM))

        val encodedBurgers = json.encodeToString(burgers)
        val encodedDrinks = json.encodeToString(drinks)
        val encodedDesserts = json.encodeToString(desserts)

        assertEquals(burgers, json.decodeFromString<MutableList<Burger>>(encodedBurgers))
        assertEquals(drinks, json.decodeFromString<MutableList<Drink>>(encodedDrinks))
        assertEquals(desserts, json.decodeFromString<MutableList<Dessert>>(encodedDesserts))
    }
}