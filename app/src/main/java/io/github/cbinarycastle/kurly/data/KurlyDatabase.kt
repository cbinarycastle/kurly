package io.github.cbinarycastle.kurly.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.cbinarycastle.kurly.data.product.HeartDao
import io.github.cbinarycastle.kurly.data.product.HeartEntity

internal const val DATABASE_NAME = "kurly"

@Database(
    entities = [HeartEntity::class],
    version = 1
)
abstract class KurlyDatabase : RoomDatabase() {
    abstract fun heartDao(): HeartDao
}