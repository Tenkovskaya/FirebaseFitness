package com.tenkovskaya.fitnes.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tenkovskaya.fitnes.data.database.user.UserInfo
import com.tenkovskaya.fitnes.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun UserScreen(navController: NavController, userViewModel: UserViewModel = viewModel(), userAvatarUrl: String) {
    val user by userViewModel.user.observeAsState()
    var userName by remember { mutableStateOf(user?.name ?: "") }
    var userWeight by remember { mutableStateOf(user?.weight ?: "") }
    var userHeight by remember { mutableStateOf(user?.height ?: "") }
    var userSex by remember { mutableStateOf(user?.sex ?: "") }
    var userActivityLevel by remember { mutableStateOf(user?.activityLevel ?: "Low") }
    var isEditingName by remember { mutableStateOf(false) }
    var isEditingWeight by remember { mutableStateOf(false) }
    var isEditingHeight by remember { mutableStateOf(false) }

    val activityLevels = listOf("Low", "Medium", "High")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(userAvatarUrl),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable {
                },
            contentScale = ContentScale.Crop
        )

        if (isEditingName) {
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Enter User Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        } else {
            Text(
                text = user?.name ?: "No user",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isEditingName = true },
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isEditingWeight) {
            TextField(
                value = userWeight,
                onValueChange = { userWeight = it },
                label = { Text("Enter Weight (kg)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        } else {
            Text(
                text = "Weight: ${userWeight.ifBlank { "Not specified" }}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isEditingWeight = true },
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isEditingHeight) {
            TextField(
                value = userHeight,
                onValueChange = { userHeight = it },
                label = { Text("Enter Height (cm)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        } else {
            Text(
                text = "Height: ${userHeight.ifBlank { "Not specified" }}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isEditingHeight = true },
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Select Sex:")
        Row {
            RadioButton(
                selected = userSex == "Male",
                onClick = { userSex = "Male" }
            )
            Text("Male")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = userSex == "Female",
                onClick = { userSex = "Female" }
            )
            Text("Female")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Select Activity Level:")
        DropdownMenu(
            expanded = true,
            onDismissRequest = { /* Handle dismiss */ }
        ) {
//            activityLevels.forEach { level ->
//                DropdownMenuItem(onClick = { userActivityLevel = level }) {
//                    Text(text = level)
//                }
//            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Here goes the Text or other UI element to display calculated daily calorie allowance

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (userName.isNotBlank() && userWeight.isNotBlank() && userHeight.isNotBlank()) {
                    userViewModel.insert(UserInfo(name = userName, weight = userWeight, height = userHeight, sex = userSex, activityLevel = userActivityLevel))
                    isEditingName = false
                    isEditingWeight = false
                    isEditingHeight = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                userViewModel.deleteAll()
                userName = ""
                userWeight = ""
                userHeight = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }

        LaunchedEffect(Unit) {
            userViewModel.loadUser()
        }
    }
}

