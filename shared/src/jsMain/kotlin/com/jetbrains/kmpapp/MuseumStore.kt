package com.jetbrains.kmpapp

import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.di.initKoin
import kotlinx.coroutines.flow.first
import org.koin.core.context.GlobalContext

@JsExport
class MuseumStore {
    private val museumRepository: MuseumRepository

    init {
        initKoin()
        val koin = GlobalContext.get()
        museumRepository = koin.get()
    }

    suspend fun getObjects(): Array<MuseumObject> {
        museumRepository.refresh()
        return museumRepository.getObjects().first().toTypedArray()
    }

    suspend fun getObjectById(objectId: Int): MuseumObject? {
        museumRepository.refresh()
        return museumRepository.getObjectById(objectId).first()
    }
}
