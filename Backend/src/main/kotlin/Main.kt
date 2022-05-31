import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Order
import products.burgers.BurgerSize
import products.burgers.CheeseBurger
import products.desserts.Filling
import products.desserts.Pancake
import products.drinks.Volume
import products.drinks.Water

private val json = Json

fun main() {
    val order = Order(null,
        mutableListOf(CheeseBurger(150, BurgerSize.DOUBLE)),
        mutableListOf(Water(50, Volume.LITER)),
        mutableListOf(Pancake(75, Filling.CHOCOLATE_CREAM)))

    val encodedStr = json.encodeToString(order)
    println(encodedStr)
}