package io.github.cbinarycastle.kurly.data.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeartDao {

    @Query("SELECT * FROM heart WHERE productId IN (:productIds)")
    fun loadAllByProductIds(productIds: List<Int>): Flow<List<HeartEntity>>

    @Insert
    fun insert(heart: HeartEntity)

    @Delete
    fun delete(heart: HeartEntity)
}