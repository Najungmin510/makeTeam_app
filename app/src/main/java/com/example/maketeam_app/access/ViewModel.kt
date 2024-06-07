package com.example.maketeam_app.access

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.maketeam_app.model.BoardContent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository, application: Application) : AndroidViewModel(application){
    private val LOG = "viewModel"

    fun boardInsert(boardContent: BoardContent){
        repository.boardInsert(boardContent)
    }

}