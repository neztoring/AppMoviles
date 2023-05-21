package com.example.appmoviles.network

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonRequest
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

open class CustomJsonRequest (method: Int, url: String?, params: JSONArray,
                         responseListener: Response.Listener<JSONObject>,
                         listener: Response.ErrorListener?) :
                JsonRequest<JSONObject>(method, url, params.toString(),
                    responseListener, listener){

    override fun deliverResponse(response: JSONObject?) {
        super.deliverResponse(response)
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<JSONObject?>? {
        return try {
            val jsonString = String(response.data, Charset.forName(HttpHeaderParser.parseCharset(response.headers)))
            Response.success(JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (je: JSONException) {
            Response.error(ParseError(je))
        }
    }

}

