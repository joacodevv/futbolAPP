package com.example.adad

import android.content.Context
import com.example.adad.db.UNDB

object Graph {
    private lateinit var db: UNDB
        private set

    val repository by lazy {
        Repository(unDao = db.unDao())
    }

    fun provide(context: Context) {
        db = UNDB.getDatabase(context)
    }

}