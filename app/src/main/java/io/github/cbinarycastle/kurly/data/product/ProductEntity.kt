package io.github.cbinarycastle.kurly.data.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.cbinarycastle.kurly.domain.model.Product

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "section_id") val sectionId: Int,
    val name: String,
    val image: String,
    @ColumnInfo(name = "original_price") val originalPrice: Int,
    @ColumnInfo(name = "discounted_price") val discountedPrice: Int?,
    @ColumnInfo(name = "is_sold_out") val isSoldOut: Boolean,
    @ColumnInfo(name = "is_liked") val isLiked: Boolean,
)

fun ProductEntity.toProduct() = Product(
    id = id,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut,
    isLiked = isLiked
)

fun Product.toEntity(sectionId: Int) = ProductEntity(
    id = id,
    sectionId = sectionId,
    name = name,
    image = image,
    originalPrice = originalPrice,
    discountedPrice = discountedPrice,
    isSoldOut = isSoldOut,
    isLiked = isLiked
)