package woowacourse.shopping.data.recentproduct

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RecentProductDao {
    @Upsert
    fun upsertRecentProduct(recentProduct: RecentProduct)

    @Query("SELECT * FROM recent_product WHERE userInfo = :userInfo AND serverUrl = :serverUrl ORDER BY timestamp DESC")
    fun loadAllRecentProducts(userInfo: String, serverUrl: String): List<RecentProduct>
}
