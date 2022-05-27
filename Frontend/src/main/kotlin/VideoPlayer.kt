
import csstype.*
import kotlinVideo.KotlinVideo
import react.FC
import react.Props
import react.css.css
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
            top = 250.px
            right = 30.pct
        }
        h3 {
            +props.video.title
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

        div{
            css {
                display = Display.flex
                marginBottom = 10.px
            }
            img{
                src = props.video.image
            }
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