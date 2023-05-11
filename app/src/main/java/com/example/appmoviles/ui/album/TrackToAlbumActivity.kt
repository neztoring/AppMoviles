package com.example.appmoviles.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appmoviles.R

class TrackToAlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_to_album)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrackToAlbumFragment.newInstance())
                .commitNow()
        }
    }
}