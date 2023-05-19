package com.example.appmoviles.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.appmoviles.R
import com.example.appmoviles.ui.album.AlbumActivity
import com.example.appmoviles.ui.performer.PerformerActivity
import com.google.android.material.navigation.NavigationView

class MenuUsuarioActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)

        val drawerLayoutCollector = findViewById<View>(R.id.drawerLayoutUser) as DrawerLayout
        val navViewCollector = findViewById<View>(R.id.navViewUser) as NavigationView


        toggle = ActionBarDrawerToggle(this, drawerLayoutCollector, R.string.open, R.string.close)
        drawerLayoutCollector.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intentPerformer = Intent(this, PerformerActivity::class.java)
        val intentAlbum = Intent (this, AlbumActivity::class.java)
        intentPerformer.putExtra("favorites", false)
        navViewCollector.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.performer -> startActivity(intentPerformer)
                R.id.albums -> startActivity(intentAlbum)
                R.id.profile_change_user -> this.finish()
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

