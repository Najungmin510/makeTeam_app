package com.example.maketeam_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// 버전 1에서 2로의 마이그레이션 정의
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE boardcontent ADD COLUMN isEnd INTEGER NOT NULL DEFAULT 0")
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideAppDataBase(
        @ApplicationContext context : Context
    ): DataBase = Room.databaseBuilder(context, DataBase::class.java,"board.db")
        .addMigrations(MIGRATION_1_2)
        .build()

    @Singleton
    @Provides
    fun provideDBDao(database : DataBase) : DBDao = database.dbDao()
}