package com.tenkovskaya.fitnes.presentation.viewmodel

import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    val userAvatarUrl = "https://example.com/avatar.png"
    val userName = "John Doe"
    val workouts = listOf("Workout 1", "Workout 2", "Workout 3")
}
