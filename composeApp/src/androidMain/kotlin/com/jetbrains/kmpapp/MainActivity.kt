package com.jetbrains.kmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetbrains.kmpapp.screens.DetailScreen
import com.jetbrains.kmpapp.screens.ListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colors = if (isSystemInDarkTheme()) darkColors() else lightColors()
            ) {
                Surface {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(navigateToDetails = { objectId ->
                navController.navigate("details/$objectId")
            })
        }
        composable(
            "details/{objectId}",
            arguments = listOf(navArgument("objectId") { type = NavType.IntType })
        ) { backstack ->
            DetailScreen(
                objectId = backstack.arguments?.getInt("objectId")!!,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
