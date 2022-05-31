package products.desserts

import com.fasterxml.jackson.annotation.JsonProperty

@kotlinx.serialization.Serializable
data class Pie(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Pancake(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Donut(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}