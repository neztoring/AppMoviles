package com.example.appmoviles.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.appmoviles.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.appmoviles.R.layout.activity_main)

        val buttonUsuarioClick = findViewById<Button>(R.id.button_usuario)
        buttonUsuarioClick.setOnClickListener {
            val intent = Intent(this, MenuUsuarioActivity::class.java)
            startActivity(intent)
        }

        val buttonCollecionistaClick = findViewById<Button>(R.id.button_coleccionista)
        buttonCollecionistaClick.setOnClickListener {
            val intent = Intent(this, MenuColeccionistaActivity::class.java)
            startActivity(intent)
        }
    }
}