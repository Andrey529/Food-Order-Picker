import csstype.*
import products.ProductType
import products.ProductWithLink
import products.burgers.BurgerSize
import products.desserts.Filling
import products.drinks.Volume
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.h4
import react.dom.html.ReactHTML.img

external interface VideoPlayerProps : Props {
    var video: ProductWithLink
    var onWatchedButtonPressed: (ProductWithLink) -> Unit
    var unwatchedVideo: Boolean
    var sizeBurger: BurgerSize
    var sizeDrink: Volume
    var fillDessert: Filling
}

val VideoPlayer = FC<VideoPlayerProps> { props ->
    div {
        css {
            position = Position.absolute
            top = 250.px
            right = 30.pct
        }
        h3 {
            +props.video.type.toString()
        }
        h4 {
            +"price:${props.video.product.price}"
        }
        when (props.video.type) {
            ProductType.BIGTASTY, ProductType.HAMBURGER,ProductType.CHEESEBURGER,ProductType.BIGMAC -> {
                button {
                    css {
                        display = Display.block
                        backgroundColor = NamedColor.lightgreen
                        width = 250.px
                        textAlign = TextAlign.center
                    }
                    onClick = {
                        props.onWatchedButtonPressed(props.video)
                        props.sizeBurger = BurgerSize.SINGLE
                    }
                    +"Добавить в заказ стандартный размер"
                }
                button {
                    css {
                        display = Display.block
                        backgroundColor = NamedColor.lightgreen
                        width = 250.px
                        textAlign = TextAlign.center
                    }
                    onClick = {
                        props.onWatchedButtonPressed(props.video)
                        props.sizeBurger = BurgerSize.DOUBLE
                    }
                    +"Добавить в заказ увеличенный размер"
                }
            }
            ProductType.FANTA, ProductType.COLA, ProductType.CHERRY, ProductType.WATER -> {
                button {
                    css {
                        display = Display.block
                        backgroundColor = NamedColor.lightgreen
                        width = 250.px
                        textAlign = TextAlign.center
                    }
                    onClick = {
                        props.onWatchedButtonPressed(props.video)
                        props.sizeDrink = Volume.THIRD_OF_LITER
                    }
                    +"Добавить в заказ 0.3 л"
                }
                button {
                    css {
                        display = Display.block
                        backgroundColor = NamedColor.lightgreen
                        width = 250.px
                        textAlign = TextAlign.center
                    }
                    onClick = {
                        props.onWatchedButtonPressed(props.video)
                        props.sizeDrink = Volume.TWO_THIRDS_OF_LITER
                    }
                    +"Добавить в заказ 0.6 л"
                }
                button {
                    css {
                        display = Display.block
                        backgroundColor = NamedColor.lightgreen
                        width = 250.px
                        textAlign = TextAlign.center
                    }
                    onClick = {
                        props.onWatchedButtonPressed(props.video)
                        props.sizeDrink = Volume.LITER
                    }
                    +"Добавить в заказ 1.0 л"

                }
            }
            ProductType.PANCAKE, ProductType.PIE, ProductType.DONUT -> {
                button {
                    css {
                        display = Display.block
                        backgroundColor = NamedColor.lightgreen
                        width = 250.px
                        textAlign = TextAlign.center
                    }
                    onClick = {
                        props.onWatchedButtonPressed(props.video)
                        props.fillDessert = Filling.CHOCOLATE_CREAM
                    }
                    +"Добавить в заказ с шоколадной начинкой"
                }
                button {
                    css {
                        display = Display.block
                        backgroundColor = NamedColor.lightgreen
                        width = 250.px
                        textAlign = TextAlign.center
                    }
                    onClick = {
                        props.onWatchedButtonPressed(props.video)
                        props.fillDessert = Filling.VANILLA_CREAM
                    }
                    +"Добавить в заказ с ванильной начинкой"
                }
            }
        }

        div {
            css {
                display = Display.flex
                marginBottom = 10.px
            }
            img {
                src = props.video.image
            }
        }
    }
}