import kotlinx.serialization.encodeToString
import products.Order
import products.burgers.Burger
import products.burgers.BurgerSize
import products.burgers.BurgerType
import products.desserts.Dessert
import products.desserts.DessertType
import products.desserts.Filling
import products.drinks.Drink
import products.drinks.DrinkType
import products.drinks.Volume
import products.serializer.ProductSerialization

private val json = ProductSerialization().json

fun main() {

    val order = Order(null,
        mutableListOf(Burger(150, BurgerType.CHEESEBURGER, BurgerSize.DOUBLE)),
        mutableListOf(Drink(75, DrinkType.COLA, Volume.LITER)),
        mutableListOf(Dessert(100, DessertType.PANCAKE, Filling.CHOCOLATE_CREAM))
    )

    println(json.encodeToString(order))
}