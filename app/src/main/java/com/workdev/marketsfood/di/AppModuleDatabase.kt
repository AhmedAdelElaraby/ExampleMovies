package com.workdev.marketsfood.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.workdev.marketsfood.database.InterfaceDatabase
import com.workdev.marketsfood.database.MyDatabase

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleDatabase {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "movies_database"
        ).build()
    }

    @Provides
    fun provideIntrfaceRoom(database: MyDatabase): InterfaceDatabase {
        return database.intrfaceRoom()
    }








}