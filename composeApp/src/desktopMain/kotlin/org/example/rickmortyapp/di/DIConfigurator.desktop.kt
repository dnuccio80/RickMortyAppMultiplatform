package org.example.rickmortyapp.di

import org.example.rickmortyapp.data.local.database.getDataBase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single { getDataBase() }
    }
}