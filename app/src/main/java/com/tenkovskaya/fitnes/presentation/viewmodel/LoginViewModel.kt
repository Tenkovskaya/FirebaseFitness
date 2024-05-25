package com.tenkovskaya.fitnes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenkovskaya.fitnes.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase):ViewModel() {
    fun login(email: String, password: String, onResult: (Boolean)-> Unit){
        viewModelScope.launch {
            val result = loginUseCase(email, password)
            onResult(result)
        }
    }
}