package com.example.musictre

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.musictre.databinding.ActivityAlbumDetailBinding
import com.example.musictre.model.Album

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val album = intent.getParcelableExtra<Album>("album")
        if (album != null) {
            populateDetails(album)
        }
    }

    private fun populateDetails(album: Album) {
        val fullHdImageUrl = album.artworkUrl100.replace("100x100bb", "1200x1200bb")

        binding.albumCoverDetail.load(fullHdImageUrl) {
            crossfade(true)
        }

        binding.albumTitleDetail.text = album.name
        binding.albumArtistDetail.text = album.artistName
        binding.albumGenreDetail.text = album.genres.firstOrNull()?.name ?: "Unknown Genre"
        binding.albumReleaseDateDetail.text = album.releaseDate
        binding.albumCopyrightDetail.text = album.copyright

        binding.btnItunes.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(album.url))
            startActivity(intent)
        }
      }
    }

