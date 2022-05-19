import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import model.Order
import model.User
import model.products.burgers.*
import model.products.desserts.*
import model.products.drinks.*
import model.products.serializer.ProductSerialization
import java.util.UUID


fun main() {
    val json = ProductSerialization().json
    val userId = UUID.randomUUID()
    val user = User(userId, null)
    println(user)
    val encodedUser = json.encodeToString(user)
    println(encodedUser)

    val newUser = json.decodeFromString<User>(encodedUser)
    println(newUser)

    val orderId = UUID.randomUUID()
    val order = Order(userId,
        orderId,
        mutableListOf(CheeseBurger(120, BurgerSize.DOUBLE), BigMack(150, BurgerSize.SINGLE)),
        mutableListOf(Water(50, Volume.THIRD_OF_LITER), Cola(90, Volume.TWO_THIRDS_OF_LITER)),
        mutableListOf(Pie(75, Filling.VANILLA_CREAM), Donut(50, Filling.CHOCOLATE_CREAM)))
    println(order)
    val encodedOrder = json.encodeToString(order)
    println(encodedOrder)
    val newOrder = json.decodeFromString<Order>(encodedOrder)
    println(newOrder)
}