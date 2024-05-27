package com.workdev.marketsfood.di

import com.workdev.marketsfood.network.API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun providesUrl() ="https://api.themoviedb.org/"

    @Provides
    @Singleton
    fun providesApiService(url:String) : API =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)

}