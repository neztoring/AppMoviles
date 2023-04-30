package com.example.appmoviles.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.appmoviles.models.Album
import com.example.appmoviles.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){

    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
            onError
        )
    }
}