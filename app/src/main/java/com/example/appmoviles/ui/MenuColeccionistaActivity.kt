package com.example.appmoviles.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appmoviles.R
import com.example.appmoviles.databinding.ActivityMenuUsuarioBinding

class MenuColeccionistaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_coleccionista)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}

