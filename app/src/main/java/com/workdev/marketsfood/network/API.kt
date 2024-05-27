package com.workdev.marketsfood.network



import com.workdev.marketsfood.model.DetilseMovie.Detilse
import com.workdev.marketsfood.model.Movies.MoviesData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("3/discover/movie")
    suspend fun Movies(@Header("Authorization") token:String ,@Query("page") page:Int): MoviesData

    @GET("3/search/movie")
    suspend fun search(@Header("Authorization") token:String,@Query("page") page:Int ,@Query("query") search:String):MoviesData

    @GET("3/movie/{movie_id}")
    suspend fun DetilseMovie(@Header("Authorization") token:String,@Path("movie_id") movie_id:Int): Detilse



}