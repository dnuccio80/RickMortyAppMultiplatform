package org.example.rickmortyapp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.rickmortyapp.data.RepositoryImpl
import org.example.rickmortyapp.data.remote.ApiService
import org.example.rickmortyapp.data.remote.paging.CharactersPagingSource
import org.example.rickmortyapp.domain.Repository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }

            // If we don´t change the config, all the request have been like this
            install(DefaultRequest) {
                url{
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                }
            }
        }
    }
    factoryOf(::ApiService)
    factory<Repository> { RepositoryImpl(get(), get()) }
    factoryOf(::CharactersPagingSource)

}