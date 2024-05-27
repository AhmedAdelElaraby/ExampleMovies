package com.workdev.marketsfood.Repository

import com.workdev.marketsfood.database.InterfaceDatabase
import com.workdev.marketsfood.model.Database.ModelRoom
import kotlinx.coroutines.flow.Flow


import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val InterfaceDatabase: InterfaceDatabase) {

    suspend fun insert(modelRoom: ModelRoom) {
        InterfaceDatabase.insert(modelRoom)
    }

    fun getAll(): Flow<List<ModelRoom>> {
        return InterfaceDatabase.getAll()
    }



}