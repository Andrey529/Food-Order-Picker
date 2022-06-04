
import csstype.*
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.p
import react.key

external interface OrderListProps : Props {
    var orders: List<String>
    var selectedOrder: String?
    var onSelectOrder: (String) -> Unit
}

val OrderList = FC<OrderListProps> { props ->


    for (order in props.orders) {
        p {
            css {
                background = NamedColor.lightyellow
                border = Border(2.px, LineStyle.solid, NamedColor.lightgrey)
                borderColor = NamedColor.black
                width = 150.px;
                height = 30.px;
                marginLeft = 25.px
                borderRadius = 10.px
                textAlign = TextAlign.center
            }

            key = order
            onClick = {
                props.onSelectOrder(order)
            }
            if (order == props.selectedOrder) {
                +"▶ "
            }

            +"Заказ №${order.substring(0,8)}"
        }
    }

}