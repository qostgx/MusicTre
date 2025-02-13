package com.example.musictre.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AppleResponse(
    val feed: Feed
)

data class Feed(
    val title: String,
    val country: String,
    val results: List<Album>
)

@Parcelize
data class Album(
    val name: String,
    val artistName: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val copyright: String? = "",
    val genres: List<Genre>,
    val url: String
) : Parcelable



@Parcelize
data class Genre(
    val genreId: String,
    val name: String,
    val url: String
) : Parcelable