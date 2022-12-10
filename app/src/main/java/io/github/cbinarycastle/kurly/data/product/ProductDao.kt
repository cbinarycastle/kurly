package io.github.cbinarycastle.kurly.data.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product WHERE section_id = :sectionId")
    fun loadAllBySectionId(sectionId: Int): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("UPDATE product SET is_liked = :isLiked WHERE id = :id")
    suspend fun updateLiked(id: Int, isLiked: Boolean)
}