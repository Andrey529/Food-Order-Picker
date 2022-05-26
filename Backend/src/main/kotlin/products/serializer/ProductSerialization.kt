package products.serializer

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import products.Product
import products.burgers.*
import products.desserts.*
import products.drinks.*


class ProductSerialization {
    val module = SerializersModule {
        polymorphic(Product::class) {
            polymorphic(Burger::class) {
                subclass(CheeseBurger::class)
                subclass(BigTasty::class)
                subclass(BigMack::class)
                subclass(Hamburger::class)
            }
            polymorphic(Dessert::class) {
                subclass(Pie::class)
                subclass(Pancake::class)
                subclass(Donut::class)
            }
            polymorphic(Drink::class) {
                subclass(Water::class)
                subclass(Cola::class)
                subclass(Pepsi::class)
                subclass(Fanta::class)
            }
        }
    }

    val json = Json {
        serializersModule = module
    }
}