package com.example.maketeam_app.access

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.maketeam_app.model.BoardContent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application){
    private val LOG = "viewModel"

    fun boardInsert(boardContent: BoardContent){
        Log.d(LOG,"뷰모델 접근")
        repository.boardInsert(boardContent)
    }

    fun getBoard(category : Int) : LiveData<List<BoardContent>> {
        Log.d(LOG, "뷰모델 게시글 목록 가져오기 접근")
        return repository.getBoard(category)
    }

}