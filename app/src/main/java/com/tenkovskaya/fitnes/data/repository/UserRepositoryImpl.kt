package com.tenkovskaya.fitnes.data.repository

import com.tenkovskaya.fitnes.data.api.FirebaseService
import com.tenkovskaya.fitnes.domain.repository.UserRepository

class UserRepositoryImpl(
    private val firebaseService: FirebaseService
) : UserRepository {
    override suspend fun register(email: String, password: String): Boolean {
        return firebaseService.register(email, password)
    }
}