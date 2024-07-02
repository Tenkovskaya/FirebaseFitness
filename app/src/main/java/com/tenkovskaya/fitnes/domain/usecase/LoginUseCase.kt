package com.tenkovskaya.fitnes.domain.usecase

import com.tenkovskaya.fitnes.domain.repository.user.UsersRegistersRepository

class LoginUseCase(private val usersRegistersRepository: UsersRegistersRepository) {
    suspend operator fun invoke(email: String, password: String): Boolean{
        return usersRegistersRepository.login(email, password)
    }
}