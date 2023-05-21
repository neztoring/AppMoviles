package com.example.appmoviles.ui.performer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviles.R
import com.example.appmoviles.models.Performer

class PerformerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performer_detail)
        val performerDetail = intent.getParcelableExtra<Performer>("performerDetail")

        this.title=performerDetail?.name
        val born= if (performerDetail?.birthDate!!.length>=10) performerDetail?.birthDate.substring(0,10) else "No se pudo encontrar la fecha"
        val creation = if (performerDetail?.creationDate!!.length>=10) performerDetail?.creationDate.substring(0,10) else "No se pudo encontrar la fecha"

        this.findViewById<TextView>(R.id.birthday).setText(if(born!="No se pudo encontrar la fecha") born else creation)
        this.findViewById<TextView>(R.id.description_performer).setText(performerDetail?.description)
        Glide.with(this.findViewById<ImageView>(R.id.image_performer))
            .load(performerDetail?.image).apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(this.findViewById<ImageView>(R.id.image_performer))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}

