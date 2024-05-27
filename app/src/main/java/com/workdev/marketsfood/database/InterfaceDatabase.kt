package com.workdev.marketsfood.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.workdev.marketsfood.model.Database.ModelRoom
import kotlinx.coroutines.flow.Flow


@Dao

interface InterfaceDatabase {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(modelRoom: ModelRoom)

    @Query("SELECT * FROM movies")
    fun getAll(): Flow<List<ModelRoom>>



}