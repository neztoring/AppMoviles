package com.example.appmoviles.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmoviles.R
import com.example.appmoviles.databinding.AlbumListItemBinding
import com.example.appmoviles.models.Album

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(val viewDatabinding: AlbumListItemBinding): RecyclerView.ViewHolder(viewDatabinding.root){
        companion object{
            @LayoutRes
            val LAYOUT = R.layout.album_list_item
        }
    }

    var albums: List<Album> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumListItemBinding = DataBindingUtil.inflate(
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
            Glide.with(holder.itemView)
                .load(albums[position].cover)
                .into(holder.viewDatabinding.imageListAlbum)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}