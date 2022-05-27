package kotlinVideo

import com.benasher44.uuid.Uuid

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
    var uniqueUrl: Uuid
) : Video(id, title, speaker, videoUrl, image)


