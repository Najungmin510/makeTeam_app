package com.example.maketeam_app.access

import androidx.lifecycle.LiveData
import com.example.maketeam_app.db.DBDao
import com.example.maketeam_app.model.BoardContent
import javax.inject.Inject




class Repository @Inject constructor(private val dbDao : DBDao) {

    private val LOG = "repository"

    fun boardInsert(boardContent: BoardContent) {
        dbDao.insertBoard(boardContent)
    }

    fun getBoard(category : Int) : LiveData<List<BoardContent>> {
       return dbDao.getAll(category)
    }

    fun getDetailBoard(id : Long) : BoardContent {
        return dbDao.getDetail(id)
    }

    fun getAllBoard() : LiveData<List<BoardContent>> {
        return dbDao.getAllNoSelect()
    }

    suspend fun updateBoardApply(id: Long, isEnd : Boolean) {
        dbDao.updateApply(id, isEnd)
    }

}