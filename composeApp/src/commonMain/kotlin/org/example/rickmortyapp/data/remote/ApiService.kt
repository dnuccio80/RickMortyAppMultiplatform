package org.example.rickmortyapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters
import org.example.rickmortyapp.data.remote.response.CharacterResponse
import org.example.rickmortyapp.data.remote.response.CharactersWrapperResponse

class ApiService(private val client: HttpClient) {
    suspend fun getSingleCharacter(id:Int): CharacterResponse {
        return client.get("/api/character/$id").body()
    }

    suspend fun getAllCharacters(page:Int): CharactersWrapperResponse {
        return client.get("/api/character/") {
            parameter("page", page)
        }.body()
    }

}