package com.example.appmoviles.repositories

import android.app.Application
import com.example.appmoviles.models.Album
import com.example.appmoviles.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){

    suspend fun refreshData():List<Album> {
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    suspend fun refreshDataDetail(id: Int):Album {
        return NetworkServiceAdapter.getInstance(application).getAlbum(id)
    }
}