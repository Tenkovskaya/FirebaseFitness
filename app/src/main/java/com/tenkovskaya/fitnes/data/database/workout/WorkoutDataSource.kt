package com.tenkovskaya.fitnes.data.database.workout

interface WorkoutDataSource {
    suspend fun getWorkoutDescriptions(): Map<String, String>
}