package com.workdev.marketsfood.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.workdev.marketsfood.model.Database.ModelRoom

@Database(entities = [ModelRoom::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun intrfaceRoom(): InterfaceDatabase
}