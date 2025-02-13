package com.example.musictre

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musictre.databinding.ActivityMainBinding
import com.example.musictre.model.AppleResponse
import com.example.musictre.model.Album
import com.example.musictre.network.RetrofitInstance
import com.example.musictre.ui.adapter.AlbumAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        binding.albumRecyclerView.layoutManager = GridLayoutManager(this, 2)
        fetchAlbums()
    }

    private fun fetchAlbums() {
        lifecycleScope.launch {
            try {
                val response: AppleResponse = RetrofitInstance.apiService.getTopAlbums()

                val albums: List<Album> = response.feed.results

                if (albums.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Список альбомов пуст!", Toast.LENGTH_SHORT).show()
                }
                binding.albumRecyclerView.adapter = AlbumAdapter(albums) { album ->
                    val intent = Intent(this@MainActivity, AlbumDetailActivity::class.java)
                    intent.putExtra("album", album)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}