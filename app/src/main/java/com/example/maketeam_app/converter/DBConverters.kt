package com.example.maketeam_app.converter

import androidx.room.TypeConverter
import com.example.maketeam_app.model.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DBConverters {

    /**리스트 값을 json 형태로 변환*/
    @TypeConverter
    fun fromPosition(list : List<Position>) : String? {
        return Gson().toJson(list)
    }

    /**json 형태를 string으로 변환*/
    @TypeConverter
    fun toPosition(list : String?) : List<Position>?{
        val type = object : TypeToken<List<Position>>() {}.type
        return Gson().fromJson(list, type)
    }
}