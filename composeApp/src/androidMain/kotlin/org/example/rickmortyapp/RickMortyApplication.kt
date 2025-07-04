package org.example.rickmortyapp

import android.app.Application
import org.example.rickmortyapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickMortyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@RickMortyApplication)
        }
    }
}