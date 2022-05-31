package products.drinks

import com.fasterxml.jackson.annotation.JsonProperty

@kotlinx.serialization.Serializable
data class Water(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Cola(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Cherry(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Fanta(@JsonProperty("id") override val price: Int, @JsonProperty("id") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}