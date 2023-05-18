package com.example.appmoviles.ui
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appmoviles.R
import com.example.appmoviles.ui.album.AlbumCreationActivity
import com.example.appmoviles.ui.album.AlbumActivity
import com.example.appmoviles.ui.album.TrackToAlbumActivity
import com.example.appmoviles.ui.performer.PerformerToAlbumActivity
import com.google.android.material.navigation.NavigationView


class MenuColeccionistaActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_coleccionista)

        val drawerLayoutCollector = findViewById<View>(R.id.drawerLayoutCollector) as DrawerLayout
        val navViewCollector = findViewById<View>(R.id.navViewCollector) as NavigationView


        toggle = ActionBarDrawerToggle(this, drawerLayoutCollector, R.string.open, R.string.close)
        drawerLayoutCollector.addDrawerListener(toggle)
        toggle.syncState()
        val intentAlbumCreationActivity = Intent(this, AlbumCreationActivity::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intentTrackToAlbum = Intent (this, TrackToAlbumActivity::class.java)
        val intentPerformerToAlbum = Intent (this,PerformerToAlbumActivity::class.java)
        navViewCollector.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fav_performers -> print("artistas favoritos")
                R.id.albums_handler -> print("manejar albmes")
                R.id.create_album -> startActivity(intentAlbumCreationActivity)
                R.id.associate_track -> startActivity(intentTrackToAlbum)
                R.id.associate_performer_album -> startActivity(intentPerformerToAlbum)
                R.id.profile_change -> this.finish()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}

