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

    @Query("SELECT * FROM boardcontent")
    fun getAll() : LiveData<List<BoardContent>>
}