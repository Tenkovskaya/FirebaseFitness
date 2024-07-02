package com.tenkovskaya.fitnes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tenkovskaya.fitnes.data.database.UserInfo
import com.tenkovskaya.fitnes.data.repository.UserInfoRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserInfoRepository) : ViewModel() {
    private val _user = MutableLiveData<UserInfo?>()
    val user: LiveData<UserInfo?> get() = _user

    fun insert(user: UserInfo) {
        viewModelScope.launch {
            repository.insert(user)
            _user.value = repository.getUser()
        }
    }

    fun loadUser() {
        viewModelScope.launch {
            _user.value = repository.getUser()
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
            _user.value = null
        }
    }
}

