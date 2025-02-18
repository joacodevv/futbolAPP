package com.example.adad.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UNDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(unModel: UNModel)

    @Query("SELECT * FROM UsedNumbers")
    fun getAllNums(): LiveData<List<UNModel>>


    @Query("DELETE FROM UsedNumbers")
    suspend fun cleanDB()

}