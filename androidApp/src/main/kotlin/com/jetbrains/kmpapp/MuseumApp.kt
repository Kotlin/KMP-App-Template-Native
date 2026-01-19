package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.DetailViewModel
import com.jetbrains.kmpapp.screens.ListViewModel
import org.koin.dsl.module

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            listOf(
                module {
                    factory { ListViewModel(get()) }
                    factory { DetailViewModel(get()) }
                }
            )
        )
    }
}
