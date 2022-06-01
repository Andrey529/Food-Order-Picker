
import com.benasher44.uuid.Uuid
import csstype.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import products.Product
import products.ProductType
import products.ProductWithLink
import products.burgers.Burger
import products.burgers.BurgerSize
import products.burgers.BurgerType
import products.desserts.Dessert
import products.desserts.DessertType
import products.desserts.Filling
import products.drinks.Drink
import products.drinks.DrinkType
import products.drinks.Volume
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


// BigMack 140/199
// Cheese 53/125
// BigTasty 249/325
// Hum 51/130

// Water 50/70/100
// Cola 69/79/129
// Pepsi(Cherry?) 69/79/129
// Fanta 69/79/129

// Pie 170/170
// Pancacke 130/130
// Donut 89/89


val listProducts = listOf(
    ProductWithLink(
        id = 1,
        Burger(140, BurgerType.BIGMAC, BurgerSize.SINGLE),
        ProductType.BIGMAC,
        "Images/bigmack.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 2,
        Burger(53, BurgerType.CHEESEBURGER, BurgerSize.SINGLE),
        ProductType.CHEESEBURGER,
        "Images/cheeseburger.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 3,
        Burger(249, BurgerType.BIGTASTY, BurgerSize.SINGLE),
        ProductType.BIGTASTY,
        "Images/bigtasty.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 4,
        Burger(51, BurgerType.HAMBURGER, BurgerSize.SINGLE),
        ProductType.HAMBURGER,
        "Images/burger.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),

    ProductWithLink(
        id = 5,
        Drink(50, DrinkType.WATER, Volume.THIRD_OF_LITER),
        ProductType.WATER,
        "Images/water.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 6,
        Drink(69, DrinkType.COLA, Volume.THIRD_OF_LITER),
        ProductType.COLA,
        "Images/coke.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 7,
        Drink(69, DrinkType.CHERRY, Volume.THIRD_OF_LITER),
        ProductType.CHERRY,
        "Images/coca-cola-cherry.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 8,
        Drink(69, DrinkType.FANTA, Volume.THIRD_OF_LITER),
        ProductType.FANTA,
        "Images/fanta.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),

    ProductWithLink(
        id = 9,
        Dessert(170, DessertType.PIE, Filling.VANILLA_CREAM),
        ProductType.PIE,
        "Images/pie.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 10,
        Dessert(130, DessertType.PANCAKE, Filling.VANILLA_CREAM),
        ProductType.PANCAKE,
        "Images/pancake.png",
        Uuid(Random.nextLong(), Random.nextLong())
    ),
    ProductWithLink(
        id = 11,
        Dessert(89, DessertType.DONUT, Filling.VANILLA_CREAM),
        ProductType.DONUT,
        "Images/donut.png",
        Uuid(Random.nextLong(), Random.nextLong())
    )
)


val mainScope = MainScope()

val App = FC<Props> {
    var currentWatchVideo: ProductWithLink? by useState(null)
    var currentUnWatchVideo: ProductWithLink? by useState(null)

    var unwatchedVideos: List<ProductWithLink> by useState(emptyList())
    var watchedVideos: List<ProductWithLink> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = listProducts
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
                        src = "Images/pie.png"
                        title = "Перейти в раздел Десертов"
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
                }
                VideoList {
                    videos = unwatchedVideos
                    selectedVideo = currentUnWatchVideo
                    onSelectVideo = { video ->
                        currentUnWatchVideo = video
                    }
                }
                // BigMack 140/199
                // Cheese 53/125
                // BigTasty 249/325
                // Hum 51/130

                // Water 50/70/100
                // Cola 69/79/129
                // Pepsi(Cherry?) 69/79/129
                // Fanta 69/79/129

                // Pie 170/170
                // Pancacke 130/130
                // Donut 89/89
                currentUnWatchVideo?.let { curr ->
                    VideoPlayer {
                        var copy: ProductWithLink
                        var copyProduct: Product
                        video = curr
                        unwatchedVideo = curr in unwatchedVideos
                        onWatchedButtonPressed = {
                            if (video in unwatchedVideos) {
                                /*when (video.id) {
                                    1 -> {
                                        copyProduct = if (sizeBurger == BurgerSize.SINGLE) {
                                            BigMac(140, sizeBurger)
                                        } else {
                                            BigMac(199, sizeBurger)
                                        }
                                    }
                                    2 -> {
                                        copyProduct = if (sizeBurger == BurgerSize.SINGLE) {
                                            CheeseBurger(53, sizeBurger)
                                        } else {
                                            CheeseBurger(125, sizeBurger)
                                        }
                                    }
                                    3 -> {
                                        copyProduct = if (sizeBurger == BurgerSize.SINGLE) {
                                            BigTasty(249, sizeBurger)
                                        } else {
                                            BigTasty(325, sizeBurger)
                                        }
                                    }
                                    4 -> {
                                        copyProduct = if (sizeBurger == BurgerSize.SINGLE) {
                                            Hamburger(51, sizeBurger)
                                        } else {
                                            Hamburger(130, sizeBurger)
                                        }
                                    }
                                    5 -> {
                                        copyProduct = when (sizeDrink) {
                                            Volume.THIRD_OF_LITER -> {
                                                Water(50, sizeDrink)
                                            }
                                            Volume.TWO_THIRDS_OF_LITER -> {
                                                Water(70, sizeDrink)
                                            }
                                            Volume.LITER -> {
                                                Water(100, sizeDrink)
                                            }
                                        }
                                    }
                                    6 -> {
                                        copyProduct = when (sizeDrink) {
                                            Volume.THIRD_OF_LITER -> {
                                                Cola(69, sizeDrink)
                                            }
                                            Volume.TWO_THIRDS_OF_LITER -> {
                                                Cola(79, sizeDrink)
                                            }
                                            Volume.LITER -> {
                                                Cola(129, sizeDrink)
                                            }
                                        }
                                    }
                                    7 -> {
                                        copyProduct = when (sizeDrink) {
                                            Volume.THIRD_OF_LITER -> {
                                                Cherry(69, sizeDrink)
                                            }
                                            Volume.TWO_THIRDS_OF_LITER -> {
                                                Cherry(79, sizeDrink)
                                            }
                                            Volume.LITER -> {
                                                Cherry(129, sizeDrink)
                                            }
                                        }
                                    }
                                    8 -> {
                                        copyProduct = when (sizeDrink) {
                                            Volume.THIRD_OF_LITER -> {
                                                Fanta(69, sizeDrink)
                                            }
                                            Volume.TWO_THIRDS_OF_LITER -> {
                                                Fanta(79, sizeDrink)
                                            }
                                            Volume.LITER -> {
                                                Fanta(129, sizeDrink)
                                            }
                                        }
                                    }
                                    9 -> {
                                        copyProduct = if (fillDessert == Filling.VANILLA_CREAM) {
                                            Pie(170, fillDessert)
                                        } else {
                                            Pie(170, fillDessert)
                                        }
                                    }
                                    10 -> {
                                        copyProduct = if (fillDessert == Filling.VANILLA_CREAM) {
                                            Pancake(130, fillDessert)
                                        } else {
                                            Pancake(130, fillDessert)
                                        }
                                    }
                                    11 -> {
                                        copyProduct = if (fillDessert == Filling.VANILLA_CREAM) {
                                            Donut(89, fillDessert)
                                        } else {
                                            Donut(89, fillDessert)
                                        }
                                    }
                                }*/
                                copy = ProductWithLink(
                                    video.id,
                                    video.product,
                                    video.type,
                                    video.image,
                                    Uuid(Random.nextLong(), Random.nextLong())
                                )
                                // сделать реализацию строго под каждую позицию, так как имеем в ассортименте id первым параметром
                                watchedVideos = watchedVideos + copy
                            } else {
                                watchedVideos = watchedVideos - video
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