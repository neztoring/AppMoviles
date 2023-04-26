package com.example.appmoviles.ui

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appmoviles.R
import com.example.appmoviles.ui.album.AlbumActivity
import com.example.appmoviles.ui.performer.PerformerActivity

class MenuUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val textViewAlbum: TextView = findViewById(R.id.textView_album)
        textViewAlbum.setOnClickListener {
            val intent = Intent (this, AlbumActivity::class.java)
            startActivity(intent)
        }

        val imageViewAlbum: ImageView = findViewById(R.id.cantante_image)
        imageViewAlbum.setOnClickListener {
            val intent = Intent(this, PerformerActivity::class.java)
            startActivity(intent)
        }

        val textViewArtists: TextView = findViewById(R.id.textView_cantante)
        textViewArtists.setOnClickListener {
            val intent = Intent (this, PerformerActivity::class.java)
            startActivity(intent)
        }

        val imageViewArtists: ImageView = findViewById(R.id.album_image)
        imageViewArtists.setOnClickListener {
            val intent = Intent(this, AlbumActivity::class.java)
            startActivity(intent)
        }

    }


}

