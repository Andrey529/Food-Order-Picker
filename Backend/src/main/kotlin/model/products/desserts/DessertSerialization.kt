package model.products.desserts

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

class DessertSerialization {
    val module = SerializersModule {
        polymorphic(Dessert::class) {
            subclass(Pie::class)
            subclass(Pancake::class)
            subclass(Donut::class)
        }
    }

    val json = Json {
        serializersModule = module
    }
}