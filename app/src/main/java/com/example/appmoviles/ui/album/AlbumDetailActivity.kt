package com.example.appmoviles.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviles.R
import com.example.appmoviles.models.Album

class AlbumDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
        val albumDetail = intent.getParcelableExtra<Album>("albumDetail")

        this.title=albumDetail?.name
        var releaseDate=albumDetail?.releaseDate?.substring(0,10)
        this.findViewById<TextView>(R.id.fecha_lanzamiento).setText(releaseDate)
        this.findViewById<TextView>(R.id.genero).setText(albumDetail?.genre)
        this.findViewById<TextView>(R.id.compania).setText(albumDetail?.recordLabel)
        this.findViewById<TextView>(R.id.descripcion).setText(albumDetail?.description)
        Glide.with(this.findViewById<ImageView>(R.id.image_album))
            .load(albumDetail?.cover).apply(
                RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(this.findViewById<ImageView>(R.id.image_album))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}