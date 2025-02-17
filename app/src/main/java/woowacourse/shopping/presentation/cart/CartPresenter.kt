package woowacourse.shopping.presentation.cart

import woowacourse.shopping.data.cart.CartRepository
import woowacourse.shopping.model.CartPages
import woowacourse.shopping.model.CartProducts
import woowacourse.shopping.model.Counter
import woowacourse.shopping.presentation.mapper.toDomain
import woowacourse.shopping.presentation.mapper.toPresentation
import woowacourse.shopping.presentation.model.CartProductModel
import woowacourse.shopping.presentation.model.ProductModel

class CartPresenter(
    private val view: CartContract.View,
    private val cartRepository: CartRepository,
    private val initialPage: Counter = Counter(CartPages.FIRST_PAGE),
) : CartContract.Presenter {

    private lateinit var cartPages: CartPages

    override fun loadCarts() {
        initCartPages()
    }

    private fun initCartPages() {
        cartRepository.getCartProducts {
            cartPages = CartPages(CartProducts(it), initialPage)
            updateProductsInCurrentPage()
            view.stopLoading()
        }
    }

    private fun updateProductsInCurrentPage() {
        updateCart(cartPages.getCurrentProducts())
        checkPageAble()
        updateCartSelectedInfo()
    }

    private fun checkPageAble() {
        checkRightPageAble()
        checkLeftPageAble()
    }

    override fun deleteCartProductModel(cartProductModel: CartProductModel) {
        cartRepository.deleteCartProduct(cartProductModel.cartId) {
            cartPages.deleteProducts(cartProductModel.productModel.toDomain())
            val deletedProducts = cartPages.getCurrentProducts()
            if (deletedProducts.size == 0) {
                minusPage()
                return@deleteCartProduct
            }
            updateProductsInCurrentPage()
        }
    }

    override fun addProductCartCount(cartProductModel: CartProductModel) {
        val nextCount = cartProductModel.count + CART_UNIT
        cartRepository.updateCartProductCount(cartProductModel.cartId, nextCount) {
            cartPages.addCountProducts(cartProductModel.productModel.toDomain())
            updateProductsInCurrentPage()
        }
    }

    override fun subProductCartCount(cartProductModel: CartProductModel) {
        val nextCount = cartProductModel.count - CART_UNIT
        if (nextCount < 1) return
        cartRepository.updateCartProductCount(cartProductModel.cartId, nextCount) {
            cartPages.subCountProducts(cartProductModel.productModel.toDomain())
            updateProductsInCurrentPage()
        }
    }

    override fun changeProductSelected(productModel: ProductModel) {
        cartPages.changeSelectedProduct(productModel.toDomain())
        updateCartSelectedInfo()
    }

    private fun updateCartSelectedInfo() {
        view.showAllCheckBoxIsChecked(cartPages.isAllProductSelected())
        view.showTotalPrice(cartPages.getSelectedProductsPrice())
        view.showTotalCount(cartPages.getSelectedProductsCount())
    }

    override fun plusPage() {
        cartPages.goNextPageProducts()
        updateProductsInCurrentPage()
    }

    override fun minusPage() {
        cartPages.goPreviousPageProducts()
        updateProductsInCurrentPage()
    }

    override fun selectAllProduct(isSelect: Boolean) {
        cartPages.selectPageProducts(isSelect)
        updateProductsInCurrentPage()
    }

    override fun navigateCartOrder() {
        val selectedCartIds = cartPages.getSelectedCartIds()
        if (selectedCartIds.isEmpty()) {
            view.showNotOrderAble()
            return
        }
        view.showOrderPage(selectedCartIds)
    }

    private fun updateCart(cartProducts: CartProducts) {
        view.showPageNumber(cartPages.pageNumber.value)
        view.showCartProductModels(cartProducts.toPresentation())
    }

    private fun checkRightPageAble() {
        view.showRightPageIsEnabled(cartPages.isNextPageAble())
    }

    private fun checkLeftPageAble() {
        view.showLeftPageIsEnabled(cartPages.isPreviousPageAble())
    }

    private fun CartProducts.toPresentation(): List<CartProductModel> {
        return items.map { it.toPresentation() }
    }

    companion object {
        private const val CART_UNIT = 1
    }
}
