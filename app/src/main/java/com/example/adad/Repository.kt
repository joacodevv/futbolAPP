package com.example.adad

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.adad.db.UNDao
import com.example.adad.db.UNModel
import kotlinx.coroutines.flow.Flow

class Repository(
    private val unDao: UNDao
) {
    suspend fun insertNumber(usedNumber: Int) {
        val unModel = UNModel(usedNumber)
        unDao.insert(unModel)
    }

    suspend fun cleanDB() {
        unDao.cleanDB()
    }

    fun getNumbers(): LiveData<List<UNModel>> {
        return unDao.getAllNums()

    }

    fun getNumsML(): MutableList<Int> {
        return unDao.getNumsML()
    }




}