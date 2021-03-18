package com.example.acase.ui.scores.view_model

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acase.data.remote.repository.DataRepository
import com.example.acase.data.remote.uistate.ActionStateLiveData
import kotlinx.coroutines.Dispatchers

class ScoresViewModel @ViewModelInject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    val getMatches =
        ActionStateLiveData(viewModelScope.coroutineContext + Dispatchers.Main) {
            dataRepository.getMatches()
        }
}