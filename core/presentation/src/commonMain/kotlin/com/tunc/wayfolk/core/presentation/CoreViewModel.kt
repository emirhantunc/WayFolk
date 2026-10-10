package com.tunc.wayfolk.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

abstract class CoreViewModel : ViewModel(), ViewModelBehaviour by ViewModelBehaviourDelegate() {

    init {
        setScope(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        clearScope()
    }
}