package products.drinks

import com.fasterxml.jackson.annotation.JsonProperty

@kotlinx.serialization.Serializable
data class Water(@JsonProperty("price") override val price: Int, @JsonProperty("volume") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Cola(@JsonProperty("price") override val price: Int, @JsonProperty("volume") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Cherry(@JsonProperty("price") override val price: Int, @JsonProperty("volume") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}

@kotlinx.serialization.Serializable
data class Fanta(@JsonProperty("price") override val price: Int, @JsonProperty("volume") override val volume: Volume) : Drink {
    init {
        require(price > 0) { "Price must be greater than 0" }
    }
}