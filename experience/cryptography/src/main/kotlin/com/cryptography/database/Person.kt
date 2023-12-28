package com.cryptography.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "person", indices = [Index(value = ["id"], unique = true)])
data class Person(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String? = null,
    @ColumnInfo(name = "last_name") val lastName: String? = null,
    @ColumnInfo(name = "height") val height: Double? = null,
    @ColumnInfo(name = "weight") val weight: Double? = null,
    @ColumnInfo(name = "cv_info") val cvInfo: String? = null
)
