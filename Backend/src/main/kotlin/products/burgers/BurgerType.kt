package products.burgers

@kotlinx.serialization.Serializable
enum class BurgerType(private val textValue: String) {
    CHEESEBURGER("Cheeseburger"),
    BIGTASTY("BigTasty"),
    BIGMAC("BigMac"),
    HAMBURGER("Hamburger");

    override fun toString(): String = textValue
}