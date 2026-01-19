package com.jetbrains.kmpapp

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.screens.DetailViewModel
import com.jetbrains.kmpapp.screens.ListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val museumRepository: MuseumRepository by inject()

    val listViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            ListViewModel(museumRepository)
        }
    }

    val detailViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            DetailViewModel(museumRepository)
        }
    }
}
