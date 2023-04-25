package com.example.appmoviles.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appmoviles.R
import com.example.appmoviles.databinding.AlbumItemBinding
import com.example.appmoviles.models.Album

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(val viewDatabinding: AlbumItemBinding): RecyclerView.ViewHolder(viewDatabinding.root){
        companion object{
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }

    var albums: List<Album> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDatabinding.also {
            it.album = albums[position]
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}