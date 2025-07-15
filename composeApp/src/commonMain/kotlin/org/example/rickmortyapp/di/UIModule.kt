package org.example.rickmortyapp.di

import org.example.rickmortyapp.ui.details.CharacterDetailsViewModel
import org.example.rickmortyapp.ui.home.tabs.characters.CharactersViewModel
import org.example.rickmortyapp.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::CharactersViewModel)
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharacterDetailsViewModel)
}