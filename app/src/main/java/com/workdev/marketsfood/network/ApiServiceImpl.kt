package com.workdev.marketsfood.network


import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val api: API) {
    suspend fun Movies(Token:String,Page:Int) = api.Movies(Token,Page)

   suspend fun search( token:String, page:Int,search:String) = api.search(token, page,search)
//
    suspend fun Detilse(token:String,Path:Int)=api.DetilseMovie(token,Path)
//
//    suspend fun AllMarkets()=api.AllMarkets()
//
//    suspend fun AllProduct()=api.AllProduct()
//
//    suspend fun Collcation()=api.Collcation()
//
//    suspend fun Cart(token: String)=api.Cart(token)
//
//    suspend fun Login(data:DataLoginUser)=api.Login(data)
}