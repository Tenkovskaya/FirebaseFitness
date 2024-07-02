package com.tenkovskaya.fitnes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenkovskaya.fitnes.domain.repository.workout.WorkoutRepository
import kotlinx.coroutines.launch

class WorkoutCardViewModel(private val repository: WorkoutRepository) : ViewModel() {
    private val _workoutDescriptions = MutableLiveData<Map<String, String>>()
    val workoutDescriptions: LiveData<Map<String, String>> = _workoutDescriptions

    init {
        fetchWorkoutDescriptions()
    }

    private fun fetchWorkoutDescriptions() {
        viewModelScope.launch {
            try {
                val descriptions = repository.getWorkoutDescriptions()
                Log.d("TAGGG WorkoutCardViewModel", "Descriptions fetched: $descriptions")
                _workoutDescriptions.value = descriptions
            } catch (e: Exception) {
                Log.e("TAGGG WorkoutCardViewModel", "Error fetching descriptions", e)
            }
        }
    }
}



