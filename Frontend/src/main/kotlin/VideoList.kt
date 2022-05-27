
import csstype.*
import kotlinVideo.KotlinVideo
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.p
import react.key

external interface VideoListProps : Props {
    var videos: List<KotlinVideo>
    var selectedVideo: KotlinVideo?
    var onSelectVideo: (KotlinVideo) -> Unit
}

val VideoList = FC<VideoListProps> { props ->


    for (video in props.videos) {
        p {
            css {
                background = NamedColor.lightyellow
                border = Border(2.px, LineStyle.solid, NamedColor.lightgrey)
                borderColor = NamedColor.black
                width = 270.px;
                height = 50.px;
                marginLeft = 25.px
                borderRadius = 10.px
                textAlign = TextAlign.center
            }
            key = video.id.toString()
            onClick = {
                props.onSelectVideo(video)
            }
            if (video.uniqueUrl == props.selectedVideo?.uniqueUrl) {
                +"▶ "
            }

            +video.title
        }
    }

}