package com.jetbrains.kmpapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.savedstate.read
import com.jetbrains.kmpapp.screens.DetailScreen
import com.jetbrains.kmpapp.screens.ListScreen

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            NavigationContent()
        }
    }
}

@Composable
fun NavigationContent() {
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
                objectId = backstack.arguments!!.read { getInt("objectId") },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
