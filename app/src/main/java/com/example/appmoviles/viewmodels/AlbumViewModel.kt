package com.example.appmoviles.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appmoviles.models.Album
import com.example.appmoviles.models.Track
import com.example.appmoviles.network.NetworkServiceAdapter
import com.example.appmoviles.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AlbumViewModel(application: Application): AndroidViewModel(application) {


    private val albumsRepository = AlbumRepository(application)

    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums



    private val _track = MutableLiveData<Track>()

    val track: LiveData<Track>
        get() = _track


    private val _album = MutableLiveData<Album>()

    val album: LiveData<Album>
        get() = _album


    private var trackJson = JSONObject()
    private var albumJson = JSONObject()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork(){
         try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = albumsRepository.refreshData()
                    _albums.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }


    public fun addTrackToAlbum(trackName:String, trackDuration:String, albumId:Int) {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    trackJson= JSONObject()
                    trackJson.put("name",trackName)
                    trackJson.put("duration",trackDuration)

                    var data = albumsRepository.trackToAlbum(trackJson,albumId)
                    _track.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }


    public fun addAlbum(name:String, cover:String, description:String, releaseDate:String,genre:String,recordLabel:String) {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    albumJson= JSONObject()
                    albumJson.put("name", name)
                    albumJson.put("cover", cover)
                    albumJson.put("description",description)
                    albumJson.put("releaseDate", releaseDate )
                    albumJson.put("genre", genre)
                    albumJson.put("recordLabel", recordLabel)

                    var data = albumsRepository.addAlbum(albumJson)
                    _album.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}