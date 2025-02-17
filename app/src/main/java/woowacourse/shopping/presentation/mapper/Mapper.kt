package woowacourse.shopping.presentation.mapper

import woowacourse.shopping.model.CartProduct
import woowacourse.shopping.model.Order
import woowacourse.shopping.model.OrderProduct
import woowacourse.shopping.model.Price
import woowacourse.shopping.model.Product
import woowacourse.shopping.presentation.model.CheckableCartProductModel
import woowacourse.shopping.presentation.model.OrderModel
import woowacourse.shopping.presentation.model.OrderProductModel
import woowacourse.shopping.presentation.model.ProductModel

fun CartProduct.toPresentation(): CheckableCartProductModel {
    return CheckableCartProductModel(
        cartId = cartId,
        productModel = product.toPresentation(),
        count = quantity,
        isChecked = isChecked,
    )
}

fun Product.toPresentation(): ProductModel {
    return ProductModel(
        id = id,
        imageUrl = imageUrl,
        name = name,
        price = price.value,
    )
}

fun ProductModel.toDomain(): Product {
    return Product(
        id = id,
        imageUrl = imageUrl,
        name = name,
        price = Price(price),
    )
}

fun Order.toPresentation() = OrderModel(
    orderId = orderId,
    orderDateTime = orderDateTime,
    orderProductModels = orderProducts.map { it.toPresentation() },
    totalPrice = totalPrice.value,
)

fun OrderProduct.toPresentation() = OrderProductModel(
    product.toPresentation(),
    quantity,
)
