package io.github.cbinarycastle.kurly.data.product

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalLikeDataSource(private val heartDao: HeartDao) : LikeDataSource {

    override fun getLikedProductIds(productIds: List<Int>): Flow<List<Int>> {
        return heartDao.loadAllByProductIds(productIds)
            .map { hearts -> hearts.map { heart -> heart.productId } }
    }

    override fun likeProduct(productId: Int) {
        heartDao.insert(HeartEntity(productId))
    }

    override fun unlikeProduct(productId: Int) {
        heartDao.delete(HeartEntity(productId))
    }
}