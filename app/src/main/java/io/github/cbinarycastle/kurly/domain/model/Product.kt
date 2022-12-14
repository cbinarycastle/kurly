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
    val discounted: Boolean
        get() = discountedPrice != null

    val discountRate: Double
        get() {
            return if (discountedPrice == null) {
                0.0
            } else {
                (originalPrice.toDouble() - discountedPrice.toDouble()) / originalPrice.toDouble()
            }
        }

    val salesPrice: Int
        get() = discountedPrice ?: originalPrice
}