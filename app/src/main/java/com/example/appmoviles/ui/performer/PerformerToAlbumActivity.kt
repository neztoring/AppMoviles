package com.example.appmoviles.ui.performer

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appmoviles.R
import com.example.appmoviles.databinding.ActivityPerformerToAlbumBinding
import com.example.appmoviles.models.Album
import com.example.appmoviles.models.Performer
import com.example.appmoviles.viewmodels.AlbumViewModel
import com.example.appmoviles.viewmodels.PerformerViewModel
import java.util.concurrent.TimeUnit

class PerformerToAlbumActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPerformerToAlbumBinding

    private lateinit var viewModelPerformer: PerformerViewModel
    private lateinit var performers: List<Performer>
    private lateinit var performersNames:  ArrayList<String>

    private lateinit var viewModelAlbum: AlbumViewModel
    private lateinit var albumes: List<Album>
    private lateinit var albumesNames:  ArrayList<String>


    private var positionAlbumSelected = -1
    private var positionPerformerSelected = -1


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performer_to_album)
        performers = emptyList()
        performersNames =  ArrayList<String>()
        albumes = emptyList()
        albumesNames =  ArrayList<String>()


        binding = ActivityPerformerToAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButton()
        loadPerformers()
        loadAlbumes()


    }

    private fun onNetworkError() {
        if(!viewModelPerformer.isNetworkErrorShown.value!!) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
            viewModelPerformer.onNetworkErrorShown()
        }
    }

    private fun setupButton(){
        binding.buttonSavePerformerToAlbum.setOnClickListener{ savePerformerToAlbum()}
    }

    private fun savePerformerToAlbum() {

        if(validateForm()) {
            Toast.makeText(
                this,
                "AlbumId Selected :".plus(
                    albumes.get(positionAlbumSelected).albumId.toString()
                        .plus(" ArtistId Selected : ".plus(performers.get(positionPerformerSelected).performerId.toString()))
                ),
                Toast.LENGTH_LONG
            ).show()

            clearForm()
        }

    }


    private fun loadPerformers() {
        viewModelPerformer = ViewModelProvider(this, PerformerViewModel.Factory(this.application)).get(PerformerViewModel::class.java)
        viewModelPerformer.performers.observe(this, Observer<List<Performer>> {
            it.apply {
                performers = this
            }
            performersNames.clear()
            performers.forEach { perf-> performersNames.add(perf.name) }
            with(binding.autoCompleteTextViewPerformer){
                setAdapter(
                    ArrayAdapter(context,
                        com.example.appmoviles.R.layout.list_item,performersNames)
                )
             }
        })

        viewModelPerformer.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        binding.autoCompleteTextViewPerformer.setOnItemClickListener { parent, view, position, id ->
            positionPerformerSelected = position

        }

    }

    private fun loadAlbumes() {
        viewModelAlbum = ViewModelProvider(this, AlbumViewModel.Factory(this.application)).get(AlbumViewModel::class.java)
        viewModelAlbum.albums.observe(this, Observer<List<Album>> {
            it.apply {
                albumes = this
            }
            albumesNames.clear()
            albumes.forEach { perf-> albumesNames.add(perf.name) }
            with(binding.autoCompleteTextViewAlbum){
                setAdapter(
                    ArrayAdapter(context,
                        com.example.appmoviles.R.layout.list_item,albumesNames)
                )
            }
        })

        viewModelPerformer.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        binding.autoCompleteTextViewAlbum.setOnItemClickListener { parent, view, position, id ->
            positionAlbumSelected = position

        }

    }

    private fun validateForm(): Boolean {
        var isValid = true
        binding.tilPaAlbum.error= ""
        binding.tilPaPerformer.error=""
        with(binding){

            if(positionPerformerSelected==-1||autoCompleteTextViewPerformer.text.toString().isBlank()){
                isValid = false
                tilPaPerformer.error = getString(R.string.form_required_field)
            }

            if(positionAlbumSelected==-1||autoCompleteTextViewAlbum.text.toString().isBlank()){
                isValid = false
                tilPaAlbum.error = getString(R.string.form_required_field)
            }
        }
        return isValid
    }

    private fun clearForm() {
        binding.autoCompleteTextViewPerformer.setText("")
        binding.autoCompleteTextViewAlbum.setText("")
        positionAlbumSelected=-1
        positionAlbumSelected=-1
    }








}