import csstype.*
import kotlinVideo.KotlinVideo
import react.*
import react.dom.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.css.css
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.img

suspend fun fetchVideo(id: Int): KotlinVideo {
    val response = window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}

suspend fun fetchVideos(): List<KotlinVideo> = coroutineScope {
    (1..5).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}
val mainScope = MainScope()

val App = FC<Props> {
    var currentWatchVideo: KotlinVideo? by useState(null)
    var currentUnWatchVideo: KotlinVideo? by useState(null)

    var unwatchedVideos: List<KotlinVideo> by useState(emptyList())
    var watchedVideos: List<KotlinVideo> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos()
        }
    }
    h1 {
        css {
            border = Border(5.px, LineStyle.dashed, NamedColor.green)
            textAlign = TextAlign.center
        }
        +"Hello, React+Kotlin/JS!"
    }
    div {
        h3 {
            +"Videos to watch"
        }
        VideoList {
            videos = unwatchedVideos
            selectedVideo = currentUnWatchVideo
            onSelectVideo = { video ->
                currentUnWatchVideo = video
            }
        }

        h3 {
            +"Videos watched"
        }
        VideoList {
            videos = watchedVideos
            selectedVideo = currentWatchVideo
            onSelectVideo = { video ->
                currentWatchVideo = video
            }
        }
    }
    div {
        currentUnWatchVideo?.let { curr ->
            VideoPlayer {
                video = curr
                unwatchedVideo = curr in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        //unwatchedVideos = unwatchedVideos - video
                        watchedVideos = watchedVideos + video
                    } else {
                        watchedVideos = watchedVideos - video
                        unwatchedVideos = unwatchedVideos + video
                    }
                }
            }
        }
    }
    div{
        css{
            position = Position.absolute
            top = 50.px
            right = 43.pct
        }
        button{
            css{
                //textAlign = TextAlign.center
                width = 100.px
                height = 50.px
                backgroundColor = NamedColor.lightgoldenrodyellow
            }
            onClick = {

            }
            +"Добавить в заказ"
        }
        button{
            css{
                //textAlign = TextAlign.center
                width = 100.px
                height = 50.px
                backgroundColor = NamedColor.lightgoldenrodyellow
            }
            +"Убрать из заказа"
        }
        button{
            css{
               // textAlign = TextAlign.center
                width = 100.px
                height = 50.px
                backgroundColor = NamedColor.green
            }
            onClick = {
                watchedVideos = emptyList()
            }
            +"Сделать заказ"
        }
    }

}