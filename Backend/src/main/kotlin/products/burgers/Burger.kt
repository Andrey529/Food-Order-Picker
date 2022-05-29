package products.burgers

import products.Product
import java.util.*

interface Burger : Product {
    val size: BurgerSize
    val id: UUID
}