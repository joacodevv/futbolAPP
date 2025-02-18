package com.example.adad.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [UNModel::class], exportSchema = false)
abstract class UNDB: RoomDatabase() {
    abstract fun unDao(): UNDao

    companion object{
        @Volatile
        private var INSTANCE: UNDB? = null

        fun getDatabase(context: Context): UNDB {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, UNDB::class.java, "used_numbers").build()
                INSTANCE = instance
                return instance
            }
        }

    }
}