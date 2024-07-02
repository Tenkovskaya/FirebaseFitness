package com.tenkovskaya.fitnes.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.tenkovskaya.fitnes.data.database.UserInfo
import com.tenkovskaya.fitnes.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserScreen(navController: NavController, userViewModel: UserViewModel = koinViewModel()) {
    val user by userViewModel.user.observeAsState()

    Column {
        Text(text = "Current User: ${user?.name ?: "No user"}")

        Button(onClick = { userViewModel.insert(UserInfo(name = "New User")) }) {
            Text("Add User")
        }

        Button(onClick = { userViewModel.deleteAll() }) {
            Text("Delete All Users")
        }

        LaunchedEffect(Unit) {
            userViewModel.loadUser()
        }
    }
}

