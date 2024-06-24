package com.tenkovskaya.fitnes.data.repository

import com.tenkovskaya.fitnes.data.database.WorkoutDataSource
import com.tenkovskaya.fitnes.domain.repository.WorkoutRepository

class WorkoutRepositoryImpl(private val dataSource: WorkoutDataSource) : WorkoutRepository {
    override suspend fun getWorkoutDescriptions(): Map<String, String> {
        return dataSource.getWorkoutDescriptions()
    }
}