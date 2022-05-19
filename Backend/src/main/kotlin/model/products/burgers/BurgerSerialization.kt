package model.products.burgers

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

class BurgerSerialization {
    val module = SerializersModule {
        polymorphic(Burger::class) {
            subclass(CheeseBurger::class)
            subclass(BigTasty::class)
            subclass(BigMack::class)
            subclass(Hamburger::class)
        }
    }

    val json = Json {
        serializersModule = module
    }
}