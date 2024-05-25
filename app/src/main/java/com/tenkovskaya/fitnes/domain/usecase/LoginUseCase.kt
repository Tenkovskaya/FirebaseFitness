package com.tenkovskaya.fitnes.domain.usecase

import com.tenkovskaya.fitnes.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): Boolean{
        return userRepository.login(email, password)
    }
}