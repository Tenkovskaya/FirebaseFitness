package com.tenkovskaya.fitnes.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tenkovskaya.fitnes.data.database.user.UserInfo
import com.tenkovskaya.fitnes.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel
@Composable
fun UserScreen(navController: NavController, userViewModel: UserViewModel = koinViewModel()) {
    val user by userViewModel.user.observeAsState()
    var userName by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isEditing) {
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Enter User Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        } else {
            Text(
                text = "Current User: ${user?.name ?: "No user"}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isEditing = true },
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (userName.isNotBlank()) {
                    userViewModel.insert(UserInfo(name = userName))
                    isEditing = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add User")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                userViewModel.deleteAll()
                userName = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete All Users")
        }

        LaunchedEffect(Unit) {
            userViewModel.loadUser()
        }
    }
}

//@Composable
//fun UserScreen(navController: NavController, userViewModel: UserViewModel = koinViewModel()) {
//    val user by userViewModel.user.observeAsState()
//    var userName by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(text = "Current User: ${user?.name ?: userName.ifBlank { "No user" }}")
//
//        TextField(
//            value = userName,
//            onValueChange = { userName = it },
//            label = { Text("Enter User Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Button(
//            onClick = {
//                if (userName.isNotBlank()) {
//                    userViewModel.insert(UserInfo(name = userName))
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Add User")
//        }
//
//        Button(
//            onClick = {
//                userViewModel.deleteAll()
//                userName = ""
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Delete All Users")
//        }
//
//        LaunchedEffect(Unit) {
//            userViewModel.loadUser()
//        }
//    }
//}

