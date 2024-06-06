package com.example.maketeam_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideAppDataBase(
        @ApplicationContext context : Context
    ): DataBase = Room.databaseBuilder(context, DataBase::class.java,"board.db")
        .build()

    @Singleton
    @Provides
    fun provideDBDao(database : DataBase) : DBDao = database.dbDao()
}