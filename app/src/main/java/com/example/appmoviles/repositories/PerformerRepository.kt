package com.example.appmoviles.repositories

import android.app.Application
import com.example.appmoviles.models.Performer
import com.example.appmoviles.network.NetworkServiceAdapter
import org.json.JSONArray

class PerformerRepository (val application: Application){

    suspend fun refreshData():List<Performer> {
        return NetworkServiceAdapter.getInstance(application).getPerformers()
    }

    suspend fun albumToPerformer(body: JSONArray, performerId: Int):Performer{
        return NetworkServiceAdapter.getInstance(application).putPerformerToAlbum(body, performerId)
    }
}