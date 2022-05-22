package com.example.hw4_database.fragments

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.hw4_database.database.AppDataBase

class Hm4database : Application() {

    private var _dataBase: AppDataBase? = null
    val dataBase
        get() = requireNotNull(_dataBase) {
            "oops"
        }

    override fun onCreate() {
        super.onCreate()
        //database init
        _dataBase = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "app_database"
        ).allowMainThreadQueries()
            .build()
    }
}

val Context.appDataBase: AppDataBase
    get() = when {
        this is Hm4database -> dataBase
        else -> applicationContext.appDataBase
    }