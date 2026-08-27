package com.jetbrains.kmpapp.screens

import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
actual fun listViewModel(): ListViewModel = koinViewModel<ListViewModel>()

@Composable
actual fun detailViewModel(): DetailViewModel = koinViewModel<DetailViewModel>()
