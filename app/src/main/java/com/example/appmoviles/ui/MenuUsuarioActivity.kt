package com.example.appmoviles.ui

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appmoviles.R
import com.example.appmoviles.ui.album.AlbumActivity

class MenuUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val textViewAlbum: TextView = findViewById(R.id.album_menu)
        textViewAlbum.setOnClickListener {
            val intent = Intent (this, AlbumActivity::class.java)
            startActivity(intent)
        }

        val imageViewAlbum: ImageView = findViewById(R.id.album_image)
        imageViewAlbum.setOnClickListener {
            val intent = Intent(this, AlbumActivity::class.java)
            startActivity(intent)
        }
    }


}

