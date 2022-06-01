package products.desserts

@kotlinx.serialization.Serializable
enum class DessertType(private val textValue: String) {
    PIE("Pie"),
    PANCAKE("Pancake"),
    DONUT("Donut");

    override fun toString(): String = textValue
}