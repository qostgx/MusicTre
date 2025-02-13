package com.example.musictre.network

import com.example.musictre.model.AppleResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/v2/us/music/most-played/100/albums.json")
    suspend fun getTopAlbums(): AppleResponse
}