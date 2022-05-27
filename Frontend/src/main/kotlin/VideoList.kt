import csstype.*
import kotlinVideo.KotlinVideo
import kotlinx.browser.window
import react.*
import react.css.css
import react.dom.*
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.div

external interface VideoListProps : Props {
    var videos: List<KotlinVideo>
    var selectedVideo: KotlinVideo?
    var onSelectVideo: (KotlinVideo) -> Unit
}

val VideoList = FC<VideoListProps> { props ->


    for (video in props.videos) {
        p {
            css {
                background = NamedColor.lightgrey
                width = 400.px;
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