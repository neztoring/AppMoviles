package com.example.appmoviles.ui
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appmoviles.R
import com.example.appmoviles.ui.album.AlbumActivity
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navViewCollector.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.fav_performers -> print("artistas favoritos")
                R.id.albums_handler -> print("manejar albmes")
                R.id.create_album -> print("crear album")
                R.id.associate_track -> print("Asociar track")
                R.id.profile_change -> print("Cambiar perfil")
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

