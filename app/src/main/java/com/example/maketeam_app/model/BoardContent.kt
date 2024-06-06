package com.example.maketeam_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * 제목 내용은 필수
 * */
@Entity
data class BoardContent(
    val title : String,
    val content : String,
    val deadline : String?, //마감일자
    val siteLink : String?, //사이트 링크
    val position : List<Position>? //구인 포지션
){
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}
