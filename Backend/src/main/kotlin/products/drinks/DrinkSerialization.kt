package products.drinks

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

class DrinkSerialization {
    val module = SerializersModule {
        polymorphic(Drink::class) {
            subclass(Water::class)
            subclass(Cola::class)
            subclass(Cherry::class)
            subclass(Fanta::class)
        }
    }

    val json = Json {
        serializersModule = module
    }
}