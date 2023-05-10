package com.example.appmoviles.ui.album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.appmoviles.R
import com.example.appmoviles.databinding.FragmentTrackToAlbumBinding
import com.example.appmoviles.models.Album
import com.example.appmoviles.viewmodels.AlbumViewModel


class TrackToAlbumFragment : Fragment(), AdapterView.OnItemClickListener {

    companion object {
        fun newInstance() = TrackToAlbumFragment()
    }

    private lateinit var viewModel: AlbumViewModel
    private var _binding: FragmentTrackToAlbumBinding? = null
    private val binding get() = _binding!!

    private lateinit var albums: List<Album>
    private lateinit var albumesNames: ArrayList <String>

    private var positionAlbumSelected = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
            albums = emptyList()
            albumesNames =  ArrayList<String>()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrackToAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.albums.observe(viewLifecycleOwner, Observer<List<Album>> { albumList ->
            albumList.apply {
                albums = this
            }
            albumesNames.clear()
            albums.forEach { alb-> albumesNames.add(alb.name) }

            with(binding.autoCompleteTextView3){
                setAdapter(ArrayAdapter(context,R.layout.list_item_album,albumesNames))
                onItemClickListener =this@TrackToAlbumFragment
            }
        })

        setupButton()



    }


    private fun setupButton(){
        binding.buttonSaveTrack.setOnClickListener{ saveTrackToAlbum()}
    }

    private fun validateForm(): Boolean {
        var isValid = true
        binding.tilTrackName.error= ""
        binding.tilTrackDuration.error=""
        binding.tilTrackAlbum.error=""
        with(binding){
            if(trackName.text.toString().isBlank()){
                isValid = false
                tilTrackName.error = getString(R.string.form_required_field)
            }
            if(trackDuration.text.toString().isBlank()){
                isValid = false
                tilTrackDuration.error = getString(R.string.form_required_field)
            }

            if(autoCompleteTextView3.text.toString().isBlank()){
                isValid = false
                tilTrackAlbum.error = getString(R.string.form_required_field)
            }

        }
        return isValid
    }

    private fun saveTrackToAlbum() {
        if(validateForm()) {

            val dataStr = "Name: ${binding.trackName.text.toString()}," +
                    "Duración: ${binding.trackDuration.text.toString()}," +
                    "Album: ${
                        albums.get(positionAlbumSelected).albumId.toString().plus("-")
                            .plus(albums.get(positionAlbumSelected).name)
                    }"

            Toast.makeText(context, dataStr, Toast.LENGTH_SHORT).show()

        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        positionAlbumSelected= position
    }

}