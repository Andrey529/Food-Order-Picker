package kotlinVideo

@kotlinx.serialization.Serializable
open class Video(
    open val id: Int,
    open val title: String,
    open val speaker: String,
    open val videoUrl: String,
    open val image: String
)



data class KotlinVideo(
    override val id: Int,
    override val title: String,
    override val speaker: String,
    override val videoUrl: String,
    override val image: String,
    var uniqueUrl: Int
) : Video(id, title, speaker, videoUrl, image)

data class Burger(
    var size: String,
    var name: String,
    var uniqueUrl: Int
)

