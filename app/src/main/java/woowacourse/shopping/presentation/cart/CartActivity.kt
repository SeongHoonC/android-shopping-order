package woowacourse.shopping.presentation.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import woowacourse.shopping.R
import woowacourse.shopping.data.cart.CartRemoteDataSource
import woowacourse.shopping.data.cart.DefaultCartRepository
import woowacourse.shopping.data.product.ProductRemoteDataSource
import woowacourse.shopping.data.util.RetrofitUtil
import woowacourse.shopping.databinding.ActivityCartBinding
import woowacourse.shopping.presentation.model.CartProductModel
import woowacourse.shopping.presentation.model.ProductModel
import woowacourse.shopping.presentation.order.OrderActivity
import woowacourse.shopping.presentation.productlist.ProductListActivity

class CartActivity : AppCompatActivity(), CartContract.View {
    private lateinit var binding: ActivityCartBinding

    private val presenter: CartContract.Presenter by lazy {
        val retrofit = RetrofitUtil.getInstance().retrofit
        val productDataSource = ProductRemoteDataSource(retrofit)
        val cartRepository =
            DefaultCartRepository(CartRemoteDataSource(retrofit), productDataSource)
        CartPresenter(this, cartRepository)
    }

    override fun showTotalPrice(price: Int) {
        binding.textOrderMoney.text = getString(R.string.price_format, price)
    }

    override fun showTotalCount(count: Int) {
        binding.textOrderCount.text = getString(R.string.order_count_format, count)
    }

    private val cartAdapter: CartAdapter by lazy {
        val cartListener = object : CartListener {
            override fun onAddClick(cartProductModel: CartProductModel) {
                presenter.addProductCartCount(cartProductModel)
            }

            override fun onRemoveClick(cartProductModel: CartProductModel) {
                presenter.subProductCartCount(cartProductModel)
            }

            override fun onCloseClick(cartProductModel: CartProductModel) {
                presenter.deleteCartProductModel(cartProductModel)
            }

            override fun changeSelectionProduct(productModel: ProductModel) {
                presenter.changeProductSelected(productModel)
            }
        }
        CartAdapter(listOf(), cartListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
        initCartAdapter()
    }

    private fun setView() {
        setToolBar()
        initLeftClick()
        initRightClick()
        initCheckBoxClick()
        initOrderClick()
    }

    private fun initOrderClick() {
        binding.textOrderCount.setOnClickListener {
            presenter.navigateCartOrder()
        }
    }

    override fun showPageNumber(count: Int) {
        binding.textCartPage.text = count.toString()
    }

    private fun setToolBar() {
        setSupportActionBar(binding.toolbarCart.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_back_24)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(ProductListActivity.getIntent(this))
                finish()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun initCartAdapter() {
        binding.recyclerCart.adapter = cartAdapter
        presenter.loadCarts()
    }

    override fun showCartProductModels(cartProductModels: List<CartProductModel>) {
        cartAdapter.setItems(cartProductModels)
    }

    private fun initRightClick() {
        binding.buttonRightPage.setOnClickListener {
            presenter.plusPage()
        }
    }

    private fun initLeftClick() {
        binding.buttonLeftPage.setOnClickListener {
            presenter.minusPage()
        }
    }

    override fun showRightPageIsEnabled(isEnable: Boolean) {
        binding.buttonRightPage.isClickable = isEnable
        if (isEnable) {
            binding.buttonRightPage.setImageResource(R.drawable.icon_right_page_true)
        } else {
            binding.buttonRightPage.setImageResource(R.drawable.icon_right_page_false)
        }
    }

    override fun showLeftPageIsEnabled(isEnable: Boolean) {
        binding.buttonLeftPage.isClickable = isEnable
        if (isEnable) {
            binding.buttonLeftPage.setImageResource(R.drawable.icon_left_page_true)
        } else {
            binding.buttonLeftPage.setImageResource(R.drawable.icon_left_page_false)
        }
    }

    private fun initCheckBoxClick() {
        binding.checkBoxSelectAll.setOnClickListener {
            presenter.selectAllProduct(binding.checkBoxSelectAll.isChecked)
        }
    }

    override fun showAllCheckBoxIsChecked(isChecked: Boolean) {
        binding.checkBoxSelectAll.isChecked = isChecked
    }

    override fun stopLoading() {
        binding.skeletonCarts.isVisible = false
        binding.containerCart.isVisible = true
    }

    override fun showOrderPage(cartIds: List<Long>) {
        startActivity(OrderActivity.getIntent(this, cartIds))
    }

    override fun showNotOrderAble() {
        Toast.makeText(this, getString(R.string.cart_is_empty), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, CartActivity::class.java)
        }
    }
}
