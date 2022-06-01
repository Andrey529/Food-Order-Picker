package products.drinks

@kotlinx.serialization.Serializable
enum class DrinkType(private val textValue: String) {
    WATER("Water"),
    COLA("Cola"),
    CHERRY("Cherry"),
    NONE("None"),
    FANTA("Fanta");

    override fun toString(): String = textValue
}