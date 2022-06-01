
import csstype.*
import products.ProductWithLink
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.p
import react.key

external interface VideoListProps : Props {
    var videos: List<ProductWithLink>
    var selectedVideo: ProductWithLink?
    var onSelectVideo: (ProductWithLink) -> Unit
}

val VideoList = FC<VideoListProps> { props ->


    for (video in props.videos) {
        p {
            css {
                background = NamedColor.lightyellow
                border = Border(2.px, LineStyle.solid, NamedColor.lightgrey)
                borderColor = NamedColor.black
                width = 130.px;
                height = 30.px;
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

            +video.type.toString()
        }
    }

}