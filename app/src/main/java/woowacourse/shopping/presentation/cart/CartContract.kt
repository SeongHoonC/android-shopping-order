package woowacourse.shopping.presentation.cart

import woowacourse.shopping.presentation.model.CartProductModel
import woowacourse.shopping.presentation.model.ProductModel

interface CartContract {
    interface Presenter {
        fun loadCarts()
        fun deleteCartProductModel(cartProductModel: CartProductModel)
        fun subProductCartCount(cartProductModel: CartProductModel)
        fun addProductCartCount(cartProductModel: CartProductModel)
        fun changeProductSelected(productModel: ProductModel)
        fun selectAllProduct(isSelect: Boolean)
        fun plusPage()
        fun minusPage()
        fun navigateCartOrder()
    }

    interface View {
        fun showCartProductModels(cartProductModels: List<CartProductModel>)
        fun showPageNumber(count: Int)
        fun showRightPageIsEnabled(isEnable: Boolean)
        fun showLeftPageIsEnabled(isEnable: Boolean)
        fun showTotalPrice(price: Int)
        fun showTotalCount(count: Int)
        fun showAllCheckBoxIsChecked(isChecked: Boolean)
        fun stopLoading()
        fun showOrderPage(cartIds: List<Long>)
        fun showNotOrderAble()
    }
}
