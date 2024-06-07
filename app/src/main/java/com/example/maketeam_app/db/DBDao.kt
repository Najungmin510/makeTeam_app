package com.example.maketeam_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.maketeam_app.model.BoardContent


/**게시물 추가, 삭제, 검색 때문에 필요할수도....*/
@Dao
interface DBDao {
    @Insert
    fun insertBoard(boardContent: BoardContent)

    @Update
    suspend fun updateBoard(boardContent: BoardContent)

    /**
     * 0: 학교 게시판 내용만 가져옴
     * 1: 공모전 게시판 내용만 가져옴
     * */
    @Query("SELECT * FROM boardcontent WHERE goal = :n")
    fun getAll(n : Int) : LiveData<List<BoardContent>>
}