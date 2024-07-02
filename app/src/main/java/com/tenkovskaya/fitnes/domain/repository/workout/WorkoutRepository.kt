package com.tenkovskaya.fitnes.domain.repository.workout

interface WorkoutRepository {
    suspend fun getWorkoutDescriptions(): Map<String, String>
}