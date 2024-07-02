package com.tenkovskaya.fitnes.data.repository.workout

import com.tenkovskaya.fitnes.data.database.workout.WorkoutDataSource
import com.tenkovskaya.fitnes.domain.repository.workout.WorkoutRepository

class WorkoutRepositoryImpl(private val dataSource: WorkoutDataSource) : WorkoutRepository {
    override suspend fun getWorkoutDescriptions(): Map<String, String> {
        return dataSource.getWorkoutDescriptions()
    }
}