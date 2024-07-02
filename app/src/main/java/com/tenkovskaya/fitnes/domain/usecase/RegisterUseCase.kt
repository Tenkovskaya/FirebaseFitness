package com.tenkovskaya.fitnes.domain.usecase

import com.tenkovskaya.fitnes.domain.repository.user.UsersRegistersRepository

class RegisterUseCase(private val usersRegistersRepository: UsersRegistersRepository) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return usersRegistersRepository.register(email, password)
    }
}