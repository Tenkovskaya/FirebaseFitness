package com.tenkovskaya.fitnes.data.repository.user

import com.tenkovskaya.fitnes.data.api.FirebaseService
import com.tenkovskaya.fitnes.domain.repository.user.UsersRegistersRepository

class UserRegistersRepositoryImpl(
    private val firebaseService: FirebaseService
) : UsersRegistersRepository {
    override suspend fun register(email: String, password: String): Boolean {
        return firebaseService.register(email, password)
    }

    override suspend fun login(email: String, password: String): Boolean {
        return firebaseService.login(email, password)
    }
}