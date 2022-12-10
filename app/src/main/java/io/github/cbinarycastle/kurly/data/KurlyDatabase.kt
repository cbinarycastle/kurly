package io.github.cbinarycastle.kurly.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.cbinarycastle.kurly.data.product.ProductDao
import io.github.cbinarycastle.kurly.data.product.ProductEntity

internal const val DATABASE_NAME = "kurly"

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class KurlyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}