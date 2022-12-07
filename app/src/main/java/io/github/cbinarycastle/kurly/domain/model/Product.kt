package io.github.cbinarycastle.kurly.domain.model

data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val isSoldOut: Boolean,
    val isLiked: Boolean = false,
) {
    val isDiscounted: Boolean
        get() = discountedPrice != null

    val discountRate: Double
        get() {
            return if (discountedPrice == null) {
                0.0
            } else {
                discountedPrice.toDouble() / originalPrice.toDouble()
            }
        }
}