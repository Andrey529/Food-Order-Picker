package model.products.burgers

enum class BurgerSize(private val textValue: String) {
    SINGLE("Single"),
    DOUBLE("Double");

    override fun toString(): String = textValue
}