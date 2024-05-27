package com.workdev.marketsfood.ui.Favorites.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workdev.marketsfood.Repository.DatabaseRepository
import com.workdev.marketsfood.model.Database.ModelRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class FavoritesViewModel  @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {



    fun insert(modelRoom: ModelRoom) {
        viewModelScope.launch {
            repository.insert(modelRoom)
        }
    }

    fun getAll(): Flow<List<ModelRoom>> {
        return repository.getAll()
    }















    }
