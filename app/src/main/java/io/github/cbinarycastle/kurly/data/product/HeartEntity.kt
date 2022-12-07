package io.github.cbinarycastle.kurly.data.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heart")
class HeartEntity(@PrimaryKey val productId: Int)