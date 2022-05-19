package model.products.serializer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import model.Order
import model.User
import model.products.burgers.BigMack
import model.products.burgers.BurgerSize
import model.products.burgers.CheeseBurger
import model.products.desserts.Donut
import model.products.desserts.Filling
import model.products.desserts.Pie
import model.products.drinks.Cola
import model.products.drinks.Volume
import model.products.drinks.Water
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.UUID

internal class ProductSerializationTest {

    @Test
    fun encodeUser() {
        val orderId = UUID.randomUUID()
        val userId = UUID.randomUUID()

        val user = User(userId, orderId)

        val json = ProductSerialization().json
        val encodedUser = json.encodeToString(user)

        assertEquals("{\"userId\":\"${user.userId}\",\"orderId\":\"${user.orderId}\"}", encodedUser)
    }

    @Test
    fun encodeOrder() {
        val orderId = UUID.randomUUID()
        val userId = UUID.randomUUID()

        val order = Order(userId, orderId,
            mutableListOf(CheeseBurger(120, BurgerSize.DOUBLE), BigMack(150, BurgerSize.SINGLE)),
            mutableListOf(Water(50, Volume.THIRD_OF_LITER), Cola(90, Volume.TWO_THIRDS_OF_LITER)),
            mutableListOf(Pie(75, Filling.VANILLA_CREAM), Donut(50, Filling.CHOCOLATE_CREAM)))
        val user = User(userId, orderId)

        val json = ProductSerialization().json
        val encodedOrder = json.encodeToString(order)

        assertEquals("{\"userId\":\"${order.userId}\",\"orderId\":\"${order.orderId}\",\"burgers\":[{\"type\":\"model.products.burgers.CheeseBurger\",\"price\":120,\"size\":\"DOUBLE\"},{\"type\":\"model.products.burgers.BigMack\",\"price\":150,\"size\":\"SINGLE\"}],\"drinks\":[{\"type\":\"model.products.drinks.Water\",\"price\":50,\"volume\":\"THIRD_OF_LITER\"},{\"type\":\"model.products.drinks.Cola\",\"price\":90,\"volume\":\"TWO_THIRDS_OF_LITER\"}],\"desserts\":[{\"type\":\"model.products.desserts.Pie\",\"price\":75,\"filling\":\"VANILLA_CREAM\"},{\"type\":\"model.products.desserts.Donut\",\"price\":50,\"filling\":\"CHOCOLATE_CREAM\"}]}",
            encodedOrder)
    }

    @Test
    fun decodeUser() {
        val orderId = UUID.randomUUID()
        val userId = UUID.randomUUID()

        val user = User(userId, orderId)

        val json = ProductSerialization().json
        val encodedUser = json.encodeToString(user)
        val decodedUser = json.decodeFromString<User>(encodedUser)
        assertEquals(user, decodedUser)
    }

    @Test
    fun decodeOrder() {
        val orderId = UUID.randomUUID()
        val userId = UUID.randomUUID()

        val order = Order(userId, orderId,
            mutableListOf(CheeseBurger(120, BurgerSize.DOUBLE), BigMack(150, BurgerSize.SINGLE)),
            mutableListOf(Water(50, Volume.THIRD_OF_LITER), Cola(90, Volume.TWO_THIRDS_OF_LITER)),
            mutableListOf(Pie(75, Filling.VANILLA_CREAM), Donut(50, Filling.CHOCOLATE_CREAM)))
        val user = User(userId, orderId)

        val json = ProductSerialization().json
        val encodedOrder = json.encodeToString(order)
        val decodedOrder = json.decodeFromString<Order>(encodedOrder)

        assertEquals(order, decodedOrder)
    }
}