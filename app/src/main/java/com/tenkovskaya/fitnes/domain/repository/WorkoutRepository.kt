package com.tenkovskaya.fitnes.domain.repository

interface WorkoutRepository {
    suspend fun getWorkoutDescriptions(): Map<String, String>
}