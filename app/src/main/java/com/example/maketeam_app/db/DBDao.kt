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

//    @Update
//    suspend fun updateBoard(boardContent: BoardContent)

    /**
     * 학교, 공모전 다 가져오기
     * */
    @Query("SELECT * FROM boardcontent")
    fun getAllNoSelect() : LiveData<List<BoardContent>>

    /**
     * 0: 학교 게시판 내용만 가져옴
     * 1: 공모전 게시판 내용만 가져옴
     * */
    @Query("SELECT * FROM boardcontent WHERE goal = :n")
    fun getAll(n : Int) : LiveData<List<BoardContent>>

    /**
     * 클릭한 게시물의 세부정보를 가져옴
     * */
    @Query("SELECT * FROM boardcontent WHERE id = :n")
    fun getDetail(n : Long) : BoardContent

    /**
     * 클릭한 id값을 가지고 있는 테이블의 isEnd 값 변경
     * */
    @Query("UPDATE boardcontent SET isEnd = :isEnd WHERE id = :id")
    fun updateApply(id : Long, isEnd : Boolean)
}