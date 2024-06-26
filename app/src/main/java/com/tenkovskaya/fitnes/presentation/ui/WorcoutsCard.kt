package com.tenkovskaya.fitnes.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tenkovskaya.fitnes.presentation.viewmodel.WorkoutCardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WorkoutCard(workoutTitle: String, viewModel: WorkoutCardViewModel = koinViewModel()) {
    val descriptions by viewModel.workoutDescriptions.observeAsState(initial = emptyMap())

    Log.d("TAGGG WorkoutCard", "Descriptions: $descriptions")

    val description = descriptions[workoutTitle] ?: "No description available"

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = workoutTitle, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Start Workout")
            }
        }
    }
}





