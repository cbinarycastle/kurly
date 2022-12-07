package io.github.cbinarycastle.kurly.data.product

data class GetProductsResponse(val data: List<ProductInfo>)

data class ProductInfo(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val isSoldOut: Boolean,
)