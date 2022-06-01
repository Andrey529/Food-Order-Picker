import com.benasher44.uuid.Uuid
import csstype.*
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import org.w3c.fetch.RequestInit
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
import products.serializer.ProductSerialization
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

private val json = ProductSerialization().json

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

fun test(order: List<ProductWithLink>) {

    val burgersOrder: MutableList<Burger> = mutableListOf()
    val drinksOrder: MutableList<Drink> = mutableListOf()
    val dessertsOrder: MutableList<Dessert> = mutableListOf()

    for (i in order.indices) {
        when (order[i].id) {
            in 1..4 -> {
                burgersOrder.add(order[i].product as Burger)
            }
            in 5..8 -> {
                drinksOrder.add(order[i].product as Drink)
            }
            in 9..11 -> {
                dessertsOrder.add(order[i].product as Dessert)
            }
        }
    }

    val requestOrder = products.Order(null, burgers = burgersOrder, drinks = drinksOrder, desserts = dessertsOrder)

    console.log(requestOrder)

    val encodedStr = json.encodeToString(requestOrder)

    console.log(encodedStr)

    window.fetch(
        "https://food-order-picker.azurewebsites.net/api/order",
        RequestInit(
            method = "POST", body = encodedStr
        )
    )
}

val mainScope = MainScope()

val App = FC<Props> {
    var currentOrderProduct: ProductWithLink? by useState(null)
    var currentAssortmentProduct: ProductWithLink? by useState(null)

    var assortmentProducts: List<ProductWithLink> by useState(emptyList())
    var orderProducts: List<ProductWithLink> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            assortmentProducts = listProducts
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
                        currentOrderProduct?.let { curr ->
                            val product = curr in orderProducts
                            if (product) {
                                orderProducts = orderProducts - curr
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
                        val testOrder = mutableListOf<ProductWithLink>()
                        for (i in orderProducts.indices) {
                            testOrder += ProductWithLink(
                                orderProducts[i].id,
                                orderProducts[i].product,
                                orderProducts[i].type,
                                orderProducts[i].image,
                                orderProducts[i].uniqueUrl
                            )
                        }
                        test(testOrder)
                        orderProducts = emptyList()
                    }
                    +"Сделать заказ и подождать"
                }
                ProductList {
                    products = orderProducts
                    selectedProduct = currentOrderProduct
                    onSelectProduct = { product ->
                        currentOrderProduct = product
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
                ProductList {
                    products = assortmentProducts
                    selectedProduct = currentAssortmentProduct
                    onSelectProduct = { product ->
                        currentAssortmentProduct = product
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
                currentAssortmentProduct?.let { curr ->
                    ProductPlayer {
                        var copy = listProducts[0]
                        var copyBurger = Burger(1, BurgerType.NONE, BurgerSize.SINGLE)
                        var copyDrink = Drink(1, DrinkType.NONE, Volume.TWO_THIRDS_OF_LITER)
                        var copyDessert = Dessert(1, DessertType.NONE, Filling.VANILLA_CREAM)

                        product = curr
                        assortmentProduct = curr in assortmentProducts
                        onOrderButtonPressed = {
                            if (product in assortmentProducts) {
                                when (product.id) {
                                    1 -> {
                                        copyBurger = if (sizeBurger == BurgerSize.SINGLE) {
                                            Burger(140, BurgerType.BIGMAC, BurgerSize.SINGLE)
                                        } else {
                                            Burger(199, BurgerType.BIGMAC, BurgerSize.DOUBLE)
                                        }
                                    }
                                    2 -> {
                                        copyBurger = if (sizeBurger == BurgerSize.SINGLE) {
                                            Burger(53, BurgerType.CHEESEBURGER, BurgerSize.SINGLE)
                                        } else {
                                            Burger(125, BurgerType.CHEESEBURGER, BurgerSize.DOUBLE)
                                        }
                                    }
                                    3 -> {
                                        copyBurger = if (sizeBurger == BurgerSize.SINGLE) {
                                            Burger(249, BurgerType.BIGTASTY, BurgerSize.SINGLE)
                                        } else {
                                            Burger(325, BurgerType.BIGTASTY, BurgerSize.DOUBLE)
                                        }
                                    }
                                    4 -> {
                                        copyBurger = if (sizeBurger == BurgerSize.SINGLE) {
                                            Burger(51, BurgerType.HAMBURGER, BurgerSize.SINGLE)
                                        } else {
                                            Burger(130, BurgerType.HAMBURGER, BurgerSize.DOUBLE)
                                        }
                                    }
                                    5 -> {
                                        copyDrink = if (sizeDrink == Volume.THIRD_OF_LITER) {
                                            Drink(50, DrinkType.WATER, Volume.THIRD_OF_LITER)
                                        } else if (sizeDrink == Volume.TWO_THIRDS_OF_LITER) {
                                            Drink(70, DrinkType.WATER, Volume.TWO_THIRDS_OF_LITER)
                                        } else {
                                            Drink(100, DrinkType.WATER, Volume.LITER)
                                        }
                                    }
                                    6 -> {
                                        copyDrink = if (sizeDrink == Volume.THIRD_OF_LITER) {
                                            Drink(69, DrinkType.COLA, Volume.THIRD_OF_LITER)
                                        } else if (sizeDrink == Volume.TWO_THIRDS_OF_LITER) {
                                            Drink(79, DrinkType.COLA, Volume.TWO_THIRDS_OF_LITER)
                                        } else {
                                            Drink(129, DrinkType.COLA, Volume.LITER)
                                        }
                                    }
                                    7 -> {
                                        copyDrink = if (sizeDrink == Volume.THIRD_OF_LITER) {
                                            Drink(69, DrinkType.CHERRY, Volume.THIRD_OF_LITER)
                                        } else if (sizeDrink == Volume.TWO_THIRDS_OF_LITER) {
                                            Drink(79, DrinkType.CHERRY, Volume.TWO_THIRDS_OF_LITER)
                                        } else {
                                            Drink(129, DrinkType.CHERRY, Volume.LITER)
                                        }
                                    }
                                    8 -> {
                                        copyDrink = if (sizeDrink == Volume.THIRD_OF_LITER) {
                                            Drink(69, DrinkType.FANTA, Volume.THIRD_OF_LITER)
                                        } else if (sizeDrink == Volume.TWO_THIRDS_OF_LITER) {
                                            Drink(79, DrinkType.FANTA, Volume.TWO_THIRDS_OF_LITER)
                                        } else {
                                            Drink(129, DrinkType.FANTA, Volume.LITER)
                                        }
                                    }
                                    9 -> {
                                        copyDessert = if (fillDessert == Filling.VANILLA_CREAM) {
                                            Dessert(170, DessertType.PIE, Filling.VANILLA_CREAM)
                                        } else {
                                            Dessert(170, DessertType.PIE, Filling.CHOCOLATE_CREAM)
                                        }
                                    }
                                    10 -> {
                                        copyDessert = if (fillDessert == Filling.VANILLA_CREAM) {
                                            Dessert(130, DessertType.PANCAKE, Filling.VANILLA_CREAM)
                                        } else {
                                            Dessert(130, DessertType.PANCAKE, Filling.CHOCOLATE_CREAM)
                                        }
                                    }
                                    11 -> {
                                        copyDessert = if (fillDessert == Filling.VANILLA_CREAM) {
                                            Dessert(89, DessertType.DONUT, Filling.VANILLA_CREAM)
                                        } else {
                                            Dessert(89, DessertType.DONUT, Filling.CHOCOLATE_CREAM)
                                        }
                                    }
                                }
                                if ((product.id >= 1) && (product.id <= 4)) {
                                    copy = ProductWithLink(
                                        product.id,
                                        copyBurger,
                                        product.type,
                                        product.image,
                                        Uuid(Random.nextLong(), Random.nextLong())
                                    )
                                }
                                if ((product.id >= 5) && (product.id <= 8)) {
                                    copy = ProductWithLink(
                                        product.id,
                                        copyDrink,
                                        product.type,
                                        product.image,
                                        Uuid(Random.nextLong(), Random.nextLong())
                                    )
                                }
                                if ((product.id >= 9) && (product.id <= 11)) {
                                    copy = ProductWithLink(
                                        product.id,
                                        copyDessert,
                                        product.type,
                                        product.image,
                                        Uuid(Random.nextLong(), Random.nextLong())
                                    )
                                }

                                // сделать реализацию строго под каждую позицию, так как имеем в ассортименте id первым параметром
                                orderProducts = orderProducts + copy
                            } else {
                                orderProducts = orderProducts - product
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