
import csstype.*
import products.ProductWithLink
import react.FC
import react.Props
import react.css.css
import react.dom.html.ReactHTML.p
import react.key

external interface ProductListProps : Props {
    var products: List<ProductWithLink>
    var selectedProduct: ProductWithLink?
    var onSelectProduct: (ProductWithLink) -> Unit
}

val ProductList = FC<ProductListProps> { props ->


    for (product in props.products) {
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

            key = product.id.toString()
            onClick = {
                props.onSelectProduct(product)
            }
            if (product.uniqueUrl == props.selectedProduct?.uniqueUrl) {
                +"▶ "
            }

            +product.type.toString()
        }
    }

}