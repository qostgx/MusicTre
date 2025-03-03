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
import com.example.musictre.ui.AlbumDetailActivity
import com.example.musictre.ui.adapter.AlbumAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val albumAdapter: AlbumAdapter by lazy {
        AlbumAdapter { album ->
            val intent = Intent(this, AlbumDetailActivity::class.java)
            intent.putExtra("album", album)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempBinding = ActivityMainBinding.inflate(layoutInflater)
        binding = tempBinding
        setContentView(tempBinding.root)
        setSupportActionBar(tempBinding.topAppBar) // TODO: тут лучше убрать эту строчку и если и добавлять тулбар то из хмл
        tempBinding.albumRecyclerView.layoutManager = GridLayoutManager(this, 2)
        tempBinding.albumRecyclerView.adapter = albumAdapter

        fetchAlbums()
    }

    private fun fetchAlbums() {
        lifecycleScope.launch {
            try {
                val response: AppleResponse = RetrofitInstance.apiService.getTopAlbums()
                val albums: List<Album> = response.feed.results

                albumAdapter.submitList(albums)
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}