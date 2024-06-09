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
}