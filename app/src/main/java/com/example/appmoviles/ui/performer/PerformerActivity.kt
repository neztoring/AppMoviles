package com.example.appmoviles.ui.performer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appmoviles.R

class PerformerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performer_list)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}