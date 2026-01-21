package com.jetbrains.kmpapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.jetbrains.kmpapp.screens.DetailScreen
import com.jetbrains.kmpapp.screens.ListScreen
import kotlinx.serialization.Serializable

@Serializable
data object ListDestination

@Serializable
data class DetailDestination(val objectId: Int)

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            val backStack = remember { mutableStateListOf<Any>(ListDestination) }

            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryDecorators = listOf(
                    rememberViewModelStoreNavEntryDecorator(),
                ),
                entryProvider = { key ->
                    when (key) {
                        is ListDestination -> NavEntry(key) {
                            ListScreen(navigateToDetails = { objectId ->
                                backStack.add(DetailDestination(objectId))
                            })
                        }
                        is DetailDestination -> NavEntry(key) {
                            DetailScreen(
                                objectId = key.objectId,
                                navigateBack = { backStack.removeLastOrNull() }
                            )
                        }
                        else -> NavEntry(key) { }
                    }
                }
            )
        }
    }
}
