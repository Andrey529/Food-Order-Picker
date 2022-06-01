package products.serializer

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import products.Product
import products.burgers.Burger
import products.desserts.Dessert
import products.drinks.Drink


class ProductSerialization {
    val module = SerializersModule {
        polymorphic(Product::class) {
            subclass(Burger::class)
            subclass(Drink::class)
            subclass(Dessert::class)
        }
    }

    val json = Json {
        serializersModule = module
    }
}