package products.desserts

import com.fasterxml.jackson.annotation.JsonProperty

@kotlinx.serialization.Serializable
data class Pie(@JsonProperty("price") override val price: Int, @JsonProperty("filling") override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Pancake(@JsonProperty("price") override val price: Int, @JsonProperty("filling") override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Donut(@JsonProperty("price") override val price: Int, @JsonProperty("filling") override val filling: Filling) : Dessert {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}