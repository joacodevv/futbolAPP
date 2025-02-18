package com.example.adad.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsedNumbers")
data class UNModel (
    @PrimaryKey val usedNumber: Int
)
