package com.tenkovskaya.fitnes.data.database

interface WorkoutDataSource {
    suspend fun getWorkoutDescriptions(): Map<String, String>
}