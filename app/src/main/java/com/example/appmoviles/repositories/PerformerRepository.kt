package com.example.appmoviles.repositories

import android.app.Application
import com.example.appmoviles.models.Performer
import com.example.appmoviles.network.NetworkServiceAdapter
import org.json.JSONArray
import org.json.JSONObject

class PerformerRepository (val application: Application){

    suspend fun refreshData():List<Performer> {
        return NetworkServiceAdapter.getInstance(application).getPerformers()
    }

    suspend fun albumToPerformer(body: JSONArray, performerId: Int):Performer{
        return NetworkServiceAdapter.getInstance(application).putPerformerToAlbum(body, performerId)
    }

    suspend fun saveFavoritePerformer(body: JSONObject, collectorId: Int, performerId: Int): Any {
        return NetworkServiceAdapter.getInstance(application).postFavoritePerformer(body, collectorId, performerId)
    }

    suspend fun removeFavoritePerformer(body: JSONObject, collectorId: Int, performerId: Int): Any {
        return NetworkServiceAdapter.getInstance(application).removeFavoritePerformer(body, collectorId, performerId)
    }

    suspend fun getPerformersByCollector(collectorId: Int): List<Performer> {
        return NetworkServiceAdapter.getInstance(application).getFavoritePerformer(collectorId)
    }
}