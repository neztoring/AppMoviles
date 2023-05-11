package com.example.appmoviles.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviles.R
import com.example.appmoviles.databinding.AlbumListItemBinding
import com.example.appmoviles.models.Album
import com.example.appmoviles.ui.album.AlbumDetailActivity

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
                .apply(RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_broken_image))

        }
        holder.viewDatabinding.root.setOnClickListener { v ->
            val intent = Intent(v.context, AlbumDetailActivity::class.java)
            intent.putExtra("albumDetail", albums[position])
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}