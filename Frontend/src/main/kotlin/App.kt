import csstype.*
import kotlinVideo.KotlinVideo
import kotlinx.browser.window
import react.*
import kotlinx.coroutines.*
import react.css.css
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul




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

val fetchVideos = listOf(
    KotlinVideo(1, "Burger", "mac", "https://youtu.be/PsaFVLr8t4E", "Images/burger.png", 1),
    KotlinVideo(2, "CheeseBurger", "mac", "https://youtu.be/Fzt_9I733Yg", "Images/cheeseburger.png", 2),
    KotlinVideo(3, "French-Fries", "mac", "https://youtu.be/pSiZVAeReeg", "Images/french-fries.png", 3),
    KotlinVideo(4, "Coca-Cola", "mac", "https://youtu.be/pSiZVAeReeg", "Images/coke.png", 4),
    KotlinVideo(5, "Coca-Cola Cherry", "mac", "https://youtu.be/pSiZVAeReeg", "Images/coca-cola-cherry.png", 5)

)

var Order = mutableListOf<KotlinVideo>()

val mainScope = MainScope()

val App = FC<Props> {
    var currentWatchVideo: KotlinVideo? by useState(null)
    var currentUnWatchVideo: KotlinVideo? by useState(null)

    var unwatchedVideos: List<KotlinVideo> by useState(emptyList())
    var watchedVideos: List<KotlinVideo> by useState(emptyList())

    /*useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos
        }
    }*/
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
                height = 600.px
            }
            h3 {
                css {
                    textAlign = TextAlign.center
                }
                +"Меню"
            }
            a {
                    button {
                        css{
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
                }
            }
        }
        /* div {
         h3 {
             +"Ассортимент"
         }
         VideoList {
             videos = unwatchedVideos
             selectedVideo = currentUnWatchVideo
             onSelectVideo = { video ->
                 currentUnWatchVideo = video
             }
         }

         h3 {
             +"Ваш заказ"
         }
         VideoList {
             videos = watchedVideos
             selectedVideo = currentWatchVideo
             onSelectVideo = { video ->
                 currentWatchVideo = video
             }
         }
     }*/
        /*div {
        currentUnWatchVideo?.let { curr ->
            VideoPlayer {
                var copy: KotlinVideo
                video = curr
                unwatchedVideo = curr in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        //unwatchedVideos = unwatchedVideos - video
                        copy = KotlinVideo(video.id, video.title, video.speaker, video.videoUrl, video.image, Random.nextInt())
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
    div{
        css{
            position = Position.absolute
            top = 50.px
            right = 25.pct
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

            +"Добавить бургер в заказ"
        }
        button{
            css{
                //textAlign = TextAlign.center
                width = 100.px
                height = 50.px
                backgroundColor = NamedColor.lightgoldenrodyellow
            }
            +"Убрать позицию из заказа"
            onClick = {
                currentWatchVideo?.let{ curr ->
                    var video = curr in watchedVideos
                    if(video){
                        watchedVideos -= curr
                    }
                }
            }
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
            +"Сделать заказ и подождать"
        }
    }*/
        div {
            id = "container"
            css {
                backgroundColor = NamedColor.lightgrey
                float = Float.right
                width = 25.pct
                height = 600.px
            }
            h3 {
                +"Основа"

            }
        }
        div {
            id = "qwer"
            css {
                backgroundColor = NamedColor.blueviolet
                float = Float.left
                width = 55.pct
                height = 600.px
            }
            h3 {
                +"Основа"
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
                height = 300.px
            }
            h3 {
                +"Подвал"
            }
        }
    }
}