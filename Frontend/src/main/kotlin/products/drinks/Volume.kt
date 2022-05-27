package products.drinks

enum class Volume(private val textValue: String) {
    THIRD_OF_LITER("Third of liter"),
    TWO_THIRDS_OF_LITER("Two thirds of liter"),
    LITER("Liter");

    override fun toString(): String = textValue
}