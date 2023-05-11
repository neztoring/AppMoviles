package com.example.appmoviles.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appmoviles.models.Album
import com.example.appmoviles.models.Performer
import com.example.appmoviles.models.Track
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL = "https://back-vynils-10.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums()= suspendCoroutine<List<Album>>{ cont->
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()){
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                cont.resume(list)
            },
            Response.ErrorListener{
                cont.resumeWithException(it)
            }
        ))
    }

    suspend fun getPerformers()= suspendCoroutine<List<Performer>>{ cont->
        requestQueue.add(getRequest("musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Performer>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Performer(performerId = item.getInt("id"),name = if (item.has("name")) item.getString("name") else "", image = if (item.has("image")) item.getString("image") else "", description = if (item.has("description")) item.getString("description") else "", birthDate = if (item.has("birthDate")) item.getString("birthDate") else "", creationDate = if (item.has("creationDate")) item.getString("creationDate") else "", type = if (item.has("type")) item.getString("type") else "", bandId = if (item.has("bandId")) item.getInt("bandId") else 0))
                }
                cont.resume(list.sortedBy { it.name })
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    fun postAlbum(body: JSONObject, onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums",
            body,
            Response.Listener<JSONObject> { response ->
                onComplete(response)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }


   suspend fun postTrackToAlbum(body: JSONObject, albumId: Int)= suspendCoroutine<Track>{ cont->
        requestQueue.add(postRequest("albums/".plus(albumId).plus("/tracks"),
            body,
            Response.Listener<JSONObject> { response ->
                cont.resume(Track(response.getInt("id"),response.getString("name"),response.getString("duration")))
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }
        
    private fun getRequest(path: String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener, errorListener)
    }
    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }




    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }


}