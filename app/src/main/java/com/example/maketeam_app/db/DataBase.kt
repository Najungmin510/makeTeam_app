package com.example.maketeam_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.maketeam_app.converter.DBConverters
import com.example.maketeam_app.model.BoardContent


@Database(entities = [BoardContent::class], version = 1, exportSchema = false)
@TypeConverters(DBConverters::class)
abstract class DataBase : RoomDatabase() {
    abstract fun dbDao() : DBDao
}