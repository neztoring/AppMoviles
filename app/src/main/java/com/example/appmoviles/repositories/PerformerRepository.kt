package com.example.appmoviles.repositories

import android.app.Application
import com.example.appmoviles.models.Performer
import com.example.appmoviles.network.NetworkServiceAdapter

class PerformerRepository (val application: Application){

    suspend fun refreshData():List<Performer> {
        return NetworkServiceAdapter.getInstance(application).getPerformers()
    }
}