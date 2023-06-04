package woowacourse.shopping.data.cart

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import woowacourse.shopping.data.cart.dto.CartProduct
import woowacourse.shopping.data.cart.dto.ProductInsertCartRequest

interface CartRetrofitService {

    @GET("/cart-items")
    fun requestCartProducts(
        @Header("Authorization") authorization: String,
    ): Call<List<CartProduct>>

    @POST("/cart-items")
    fun insertCartProduct(
        @Header("Authorization") authorization: String,
        @Body productInsertRequest: ProductInsertCartRequest,
    ): Call<Unit>

    @PATCH("/cart-items/{cartItemId}")
    fun updateCartProduct(
        @Header("Authorization") authorization: String,
        @Path("cartItemId") cartItemId: Long,
        @Body quantity: Int,
    ): Call<Unit>

    @DELETE("/cart-items/{cartItemId}")
    fun deleteCartProduct(
        @Header("Authorization") authorization: String,
        @Path("cartItemId") cartItemId: Long,
    ): Call<Unit>
}
