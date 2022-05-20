import csstype.*
import kotlinVideo.KotlinVideo
import kotlinVideo.Video
import react.*
import react.css.css
import react.dom.render
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.img

external interface VideoPlayerProps : Props {
    var video: KotlinVideo
    var onWatchedButtonPressed: (KotlinVideo) -> Unit
    var unwatchedVideo: Boolean
}

val VideoPlayer = FC<VideoPlayerProps> { props ->
    div {
        css {
            position = Position.absolute
            top = 10.px
            right = 10.px
        }
        h3 {
            +"${props.video.speaker}: ${props.video.title}"
        }
        button {
            css {
                display = Display.block
                backgroundColor = if (props.unwatchedVideo) NamedColor.lightgreen else NamedColor.red
            }
            onClick = {
                props.onWatchedButtonPressed(props.video)
            }
            if (props.unwatchedVideo) {
                +"Добавить в заказ"
            } else {
                +"Mark as unwatched"
            }
        }

        ReactPlayer {
            url = props.video.videoUrl
        }
        div {
            css {
                display = Display.flex
                marginBottom = 10.px
            }
            EmailShareButton {
                url = props.video.videoUrl
                EmailIcon {
                    size = 32
                    round = true
                }
            }
            TelegramShareButton {
                url = props.video.videoUrl
                TelegramIcon {
                    size = 32
                    round = true
                }
            }
        }
    }
}