package com.example.appmoviles.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.appmoviles.models.Performer
import com.example.appmoviles.network.NetworkServiceAdapter

class PerformerRepository (val application: Application){

    fun refreshData(callback: (List<Performer>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getPerformers({
            callback(it)
        },
            onError
        )
    }
}