package products.desserts

@kotlinx.serialization.Serializable
enum class DessertType(private val textValue: String) {
    PIE("Pie"),
    PANCAKE("Pancake"),
    NONE("None"),
    DONUT("Donut");

    override fun toString(): String = textValue
}