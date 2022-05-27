import com.benasher44.uuid.Uuid
import csstype.*
import kotlinVideo.KotlinVideo
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import products.ProductWithLink
import products.burgers.BigMack
import products.burgers.BurgerSize
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.useEffectOnce
import react.useState
import kotlin.random.Random


/*suspend fun fetchVideo(id: Int): KotlinVideo {
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
}*/

val listProducts = listOf(
    ProductWithLink(BigMack(390, BurgerSize.DOUBLE), "Images/burger.png", 1)
)

val fetchVideos = listOf(
    KotlinVideo(
        1,
        "Burger",
        "burger",
        "https://youtu.be/PsaFVLr8t4E",
        "Images/burger.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    KotlinVideo(
        2,
        "CheeseBurger",
        "burger",
        "https://youtu.be/Fzt_9I733Yg",
        "Images/cheeseburger.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    KotlinVideo(
        3,
        "French-Fries",
        "snack",
        "https://youtu.be/pSiZVAeReeg",
        "Images/french-fries.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    KotlinVideo(
        4,
        "Coca-Cola",
        "drink",
        "https://youtu.be/pSiZVAeReeg",
        "Images/coke.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    KotlinVideo(
        5,
        "Coca-Cola Cherry",
        "drink",
        "https://youtu.be/pSiZVAeReeg",
        "Images/coca-cola-cherry.png",
        Uuid(Random.nextLong(), Random.nextLong())
    )

)

var Order = mutableListOf<KotlinVideo>()

var filterButtons = 5

val mainScope = MainScope()

val App = FC<Props> {
    var currentWatchVideo: KotlinVideo? by useState(null)
    var currentUnWatchVideo: KotlinVideo? by useState(null)

    var unwatchedVideos: List<KotlinVideo> by useState(emptyList())
    var watchedVideos: List<KotlinVideo> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos
        }
    }
    div {
        css {
            backgroundColor = NamedColor.blue

        }
        div {
            id = "header"
            h1 {
                css {
                    //border = Border(5.px, LineStyle.dashed, NamedColor.green)
                    backgroundColor = NamedColor.coral
                    textAlign = TextAlign.center
                    height = 100.px
                }
                +"0302 Delivery Food"

                hr {
                    css {
                        width = 75.pct
                        height = 1.px
                    }
                }
            }
        }
        div {
            id = "navigation"
            css {
                backgroundColor = NamedColor.chocolate
                height = 100.px
                textAlign = TextAlign.center
            }
            h3 {
                +"Блок навигации"
            }

            ul {
                css {
                    marginLeft = 0.px
                    paddingLeft = 0.px
                    listStyle = ListStyle.none
                    textAlign = TextAlign.center
                }
                li {
                    css { display = Display.inline }
                    a {
                        css {
                            display = Display.inlineBlock
                            width = 5.em
                            padding = 10.px
                            backgroundColor = NamedColor.darkgray
                            border = Border(2.px, LineStyle.solid, NamedColor.lightgrey)
                            textDecoration = TextDecoration.none
                            color = NamedColor.white
                            textAlign = TextAlign.center
                        }
                        href = "#news"; +"Главная"
                    }
                }
                li {
                    css { display = Display.inline }
                    a {
                        css {
                            display = Display.inlineBlock
                            width = 5.em
                            padding = 10.px
                            marginLeft = 20.px
                            backgroundColor = NamedColor.darkgray
                            border = Border(2.px, LineStyle.solid, NamedColor.lightgrey)
                            textDecoration = TextDecoration.none
                            color = NamedColor.white
                            textAlign = TextAlign.center
                        }
                        href = "#news"; +"Новости"
                    }
                }
                li {
                    css { display = Display.inline }
                    a {
                        css {
                            display = Display.inlineBlock
                            width = 5.em
                            padding = 10.px
                            marginLeft = 20.px
                            backgroundColor = NamedColor.darkgray
                            border = Border(2.px, LineStyle.solid, NamedColor.lightgrey)
                            textDecoration = TextDecoration.none
                            color = NamedColor.white
                            textAlign = TextAlign.center
                        }
                        href = "#contact"; +"Меню"
                    }
                }
                li {
                    css { display = Display.inline }
                    a {
                        css {
                            display = Display.inlineBlock
                            width = 5.em
                            padding = 10.px
                            marginLeft = 20.px
                            backgroundColor = NamedColor.darkgray
                            border = Border(2.px, LineStyle.solid, NamedColor.lightgrey)
                            textDecoration = TextDecoration.none
                            color = NamedColor.white
                            textAlign = TextAlign.center
                        }
                        href = "#about"; +"О нас"
                    }
                }
            }
        }
        div {
            id = "menu"
            css {
                //textAlign = TextAlign.center
                backgroundColor = NamedColor.aquamarine
                float = Float.left
                width = 20.pct
                height = 800.px
            }
            h3 {
                css {
                    textAlign = TextAlign.center
                }
                +"Меню"
            }
            a {
                button {
                    css {
                        padding = 0.px
                        border = 0.px
                        marginLeft = 40.px
                        width = 100.px
                        height = 100.px
                    }
                    img {
                        css {
                            width = 100.px
                            height = 100.px
                            border = 0.px
                            border = Border(4.px, LineStyle.solid, NamedColor.darkred)
                            backgroundColor = NamedColor.gray
                            //marginLeft = 40.px
                        }
                        src = "Images/burger.png"
                        title = "Перейти в раздел Бургеров"
                    }
                    onClick = {
                        filterButtons = 1
                    }
                }
            }
            a {
                css {
                    marginLeft = 10.px
                }
                button {
                    css {
                        padding = 0.px
                        border = 0.px
                        width = 100.px
                        height = 100.px
                    }
                    img {
                        css {
                            width = 100.px
                            height = 100.px
                            border = 0.px
                            border = Border(4.px, LineStyle.solid, NamedColor.darkred)
                            backgroundColor = NamedColor.gray
                        }
                        src = "Images/coke.png"
                        title = "Перейти в раздел Напитков"
                    }
                    onClick = {
                        filterButtons = 2
                    }
                }
            }
            a {
                css {
                    marginLeft = 40.px
                }
                button {
                    css {
                        padding = 0.px
                        border = 0.px
                        width = 100.px
                        height = 100.px
                    }

                    img {
                        css {
                            width = 100.px
                            height = 100.px
                            border = 0.px
                            border = Border(4.px, LineStyle.solid, NamedColor.darkred)
                            backgroundColor = NamedColor.gray
                        }
                        src = "Images/french-fries.png"
                        title = "Перейти в раздел Закусок"
                    }
                    onClick = {
                        filterButtons = 3
                    }
                }
            }
            a {
                css {
                    marginLeft = 10.px
                    marginTop = 10.px
                }
                button {
                    css {
                        padding = 0.px
                        border = 0.px
                        width = 100.px
                        height = 100.px
                    }
                    img {
                        css {
                            width = 100.px
                            height = 100.px
                            border = 0.px
                            border = Border(4.px, LineStyle.solid, NamedColor.darkred)
                            backgroundColor = NamedColor.gray
                        }
                        src = "Images/ice-cream.png"
                        title = "Перейти в раздел Десертов"
                    }
                    onClick = {
                        filterButtons = 4
                    }
                }
            }
            a {
                css {

                }
                button {
                    css {
                        width = 218.px
                        height = 100.px
                        marginLeft = 40.px
                        textAlign = TextAlign.center
                        border = Border(4.px, LineStyle.solid, NamedColor.darkred)
                        backgroundColor = NamedColor.gray
                    }
                    +"Весь ассортимент"
                    onClick = {
                        filterButtons = 5
                    }
                }
            }
        }

        div {
            id = "container"
            css {
                backgroundColor = NamedColor.lightgrey
                float = Float.right
                width = 25.pct
                height = 800.px
            }

            div {
                h3 {
                    css {
                        textAlign = TextAlign.center
                    }
                    +"Ваш заказ"
                }
                button {
                    css {
                        //textAlign = TextAlign.center
                        width = 100.px
                        height = 50.px
                        marginLeft = 70.px
                        backgroundColor = NamedColor.lightgoldenrodyellow
                    }
                    +"Убрать позицию из заказа"
                    onClick = {
                        currentWatchVideo?.let { curr ->
                            val video = curr in watchedVideos
                            if (video) {
                                watchedVideos = watchedVideos - curr
                            }
                        }
                    }
                }
                button {
                    css {
                        // textAlign = TextAlign.center
                        width = 100.px
                        height = 50.px
                        backgroundColor = NamedColor.green
                    }
                    onClick = {
                        watchedVideos = emptyList()
                    }
                    +"Сделать заказ и подождать"
                }
                VideoList {
                    videos = watchedVideos
                    selectedVideo = currentWatchVideo
                    onSelectVideo = { video ->
                        currentWatchVideo = video
                    }
                }
            }

        }
        div {
            id = "list"
            css {
                backgroundColor = NamedColor.blueviolet
                float = Float.left
                width = 55.pct
                height = 800.px
            }
            div {
                h3 {
                    +"Ассортимент"
                    if(filterButtons == 3){
                        +"Ассортимент"
                    }
                }
                    VideoList {
                        videos = unwatchedVideos
                        selectedVideo = currentUnWatchVideo
                        onSelectVideo = { video ->
                            currentUnWatchVideo = video
                        }
                    }

                currentUnWatchVideo?.let { curr ->
                    VideoPlayer {
                        var copy: KotlinVideo
                        video = curr
                        unwatchedVideo = curr in unwatchedVideos
                        onWatchedButtonPressed = {
                            if (video in unwatchedVideos) {
                                //unwatchedVideos = unwatchedVideos - video
                                copy = KotlinVideo(
                                    video.id,
                                    video.title,
                                    video.speaker,
                                    video.videoUrl,
                                    video.image,
                                    Uuid(Random.nextLong(), Random.nextLong())
                                )
                                //copy.uniqueUrl = Random.nextInt()
                                watchedVideos = watchedVideos + copy
                                //сделать копию объекта видео, добавить в него уникальное свойство, добавить копию в вотчед..
                            } else {
                                watchedVideos = watchedVideos - video
                                unwatchedVideos = unwatchedVideos + video
                            }
                        }
                    }
                }


            }
        }
        div {
            id = "clear"
            css {
                clear = Clear.both
            }
        }
        div {
            id = "footer"
            css {
                backgroundColor = NamedColor.black
                height = 150.px
                color = NamedColor.white
                textAlign = TextAlign.center
            }
            h3 {
                +"Подвал"
            }
        }
    }
}