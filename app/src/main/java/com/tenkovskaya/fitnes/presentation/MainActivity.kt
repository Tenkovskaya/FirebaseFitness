package com.tenkovskaya.fitnes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.tenkovskaya.fitnes.presentation.theme.FitnessAppTheme
import com.tenkovskaya.fitnes.presentation.ui.DashboardScreen
import com.tenkovskaya.fitnes.presentation.ui.LoginScreen
import com.tenkovskaya.fitnes.presentation.ui.RegisterScreen
import com.tenkovskaya.fitnes.presentation.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppTheme {
                val navController = rememberNavController()
                val auth = FirebaseAuth.getInstance()
                val coroutineScope = rememberCoroutineScope()
                LaunchedEffect(Unit) {
                    coroutineScope.launch{
                        if (auth.currentUser != null){
                            navController.navigate("dashboard"){
                                popUpTo(0){inclusive = true}
                            }
                        }
                    }
                }
                SetupNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "register") {
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("dashboard") {
            val dashboardViewModel: DashboardViewModel = koinViewModel()
            DashboardScreen(
                navController = navController,
                userAvatarUrl = dashboardViewModel.userAvatarUrl,
                userName = dashboardViewModel.userName,
                workouts = dashboardViewModel.workouts
            )
        }
    }
}