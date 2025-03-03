package com.example.musictre.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.musictre.databinding.ItemAlbumBinding
import com.example.musictre.model.Album

class AlbumAdapter(
    private val onItemClick: (Album) -> Unit
) : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback()) {

    inner class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) { // TODO: убери импорт, оставь только RecyclerView.ViewHolder
        fun bind(album: Album) {
            binding.albumTitle.text = album.name
            binding.albumArtist.text = album.artistName
            val fullHdImageUrl = album.artworkUrl100.replace("100x100bb", "1920x1080bb") // TODO: вьюха - тупая и красивая, оператор реплейс тут не должен быть
            binding.albumCover.load(fullHdImageUrl) {
                crossfade(true)
            }
            binding.root.setOnClickListener {
                onItemClick(album)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
    }
}
