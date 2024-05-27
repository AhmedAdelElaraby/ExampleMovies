package com.workdev.marketsfood.Repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.workdev.marketsfood.model.Error.ErrorResponse
import retrofit2.HttpException

fun convertErrorBody(throwable: HttpException): ErrorResponse?{

    return try {
        val error=throwable.response()?.errorBody()?.charStream()
        val gson=Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type
        var errorResponse: ErrorResponse? =gson.fromJson(error,type)
        errorResponse
    } catch (exception :Exception){

        null
    }

}