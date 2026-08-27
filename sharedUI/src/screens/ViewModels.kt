package com.jetbrains.kmpapp.screens

import androidx.compose.runtime.Composable

/**
 * Resolves the screens' ViewModels from Koin.
 *
 * On Android and desktop, KMP-ObservableViewModel's `ViewModel` is a Jetpack ViewModel, so
 * `koinViewModel()` can retain these across configuration changes. It is a standalone class on
 * Kotlin/Wasm, where the web app ties them to the composition instead.
 */
@Composable
expect fun listViewModel(): ListViewModel

@Composable
expect fun detailViewModel(): DetailViewModel
