package model.products.desserts

enum class Filling(private val textValue: String) {
    CHOCOLATE_CREAM("Chocolate cream"),
    VANILLA_CREAM("Vanilla cream");

    override fun toString(): String = textValue
}