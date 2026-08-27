package com.jetbrains.kmpapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import com.rickclephas.kmp.observableviewmodel.ViewModel
import org.koin.compose.getKoin

@Composable
actual fun listViewModel(): ListViewModel = rememberViewModel()

@Composable
actual fun detailViewModel(): DetailViewModel = rememberViewModel()

@Composable
private inline fun <reified T : ViewModel> rememberViewModel(): T {
    val koin = getKoin()
    val viewModel = remember { koin.get<T>() }
    DisposableEffect(viewModel) {
        onDispose { viewModel.clear() }
    }
    return viewModel
}
