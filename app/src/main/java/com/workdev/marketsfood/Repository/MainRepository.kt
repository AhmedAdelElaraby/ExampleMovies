package com.workdev.marketsfood.Repository

import com.workdev.marketsfood.model.DetilseMovie.Detilse
import com.workdev.marketsfood.model.Movies.MoviesData
import com.workdev.marketsfood.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun Movies(Token: String, Page: Int): Flow<MoviesData> = flow {
        emit(apiServiceImpl.Movies(Token, Page))
    }.flowOn(Dispatchers.IO)

    fun search(token: String, page: Int, search: String): Flow<MoviesData> = flow {
        emit(apiServiceImpl.search(token, page, search))
    }.flowOn(Dispatchers.IO)


    fun Detilse(token: String,Path:Int): Flow<Detilse> = flow {
        emit(apiServiceImpl.Detilse(token,Path))
    }.flowOn(Dispatchers.IO)


}