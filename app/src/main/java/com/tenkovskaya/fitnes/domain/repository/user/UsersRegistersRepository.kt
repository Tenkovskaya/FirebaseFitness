package com.tenkovskaya.fitnes.domain.repository.user

interface UsersRegistersRepository {
    suspend fun register(email: String, password: String): Boolean
    suspend fun login(email: String, password: String): Boolean
}