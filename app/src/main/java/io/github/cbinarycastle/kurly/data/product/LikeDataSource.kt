package io.github.cbinarycastle.kurly.data.product

import kotlinx.coroutines.flow.Flow

interface LikeDataSource {

    fun getLikedProductIds(productIds: List<Int>): Flow<List<Int>>
}