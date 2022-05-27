package products.burgers

@kotlinx.serialization.Serializable
enum class BurgerSize(private val textValue: String) {
    SINGLE("Single"),
    DOUBLE("Double");

    override fun toString(): String = textValue
}