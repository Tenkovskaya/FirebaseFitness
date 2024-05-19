package com.tenkovskaya.fitnes.domain.repository

interface UserRepository {
    suspend fun register(email: String, password: String): Boolean
}