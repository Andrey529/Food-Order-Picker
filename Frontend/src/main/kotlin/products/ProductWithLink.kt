package products

import com.benasher44.uuid.Uuid

data class ProductWithLink(val id: Int, val product: Product, val type: ProductType, val image: String, var uniqueUrl: Uuid)

enum class ProductType(private val textValue: String){
    CHEESEBURGER("Cheeseburger"),
    BIGTASTY("BigTasty"),
    BIGMAC("BigMac"),
    HAMBURGER("Hamburger"),
    PIE("Pie"),
    PANCAKE("Pancake"),
    DONUT("Donut"),
    WATER("Water"),
    COLA("Cola"),
    CHERRY("Cherry"),
    FANTA("Fanta");

    override fun toString(): String = textValue
}
