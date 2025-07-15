package org.example.rickmortyapp.di

import org.example.rickmortyapp.domain.GetCharacterOfTheDayUseCase
import org.example.rickmortyapp.domain.GetMultipleEpisodesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCharacterOfTheDayUseCase)
    factoryOf(::GetMultipleEpisodesUseCase)
}