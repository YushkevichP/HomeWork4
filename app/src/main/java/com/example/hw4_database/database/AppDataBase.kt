package com.example.hw4_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw4_database.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDaoFun(): UserDao

}