package com.example.maketeam_app.model


/**
 * 제목 내용은 필수
 * */
data class BoardContent(
    val title : String,
    val content : String,
    val deadline : String?,
    val siteLink : String?,
    val position : List<Position>?
)
