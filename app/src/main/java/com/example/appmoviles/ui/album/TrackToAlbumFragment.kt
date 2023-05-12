package com.example.appmoviles.ui.album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import com.example.appmoviles.models.Track
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


    private lateinit var track: Track

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

            with(binding.autoCompleteTextViewAlbum){
                setAdapter(ArrayAdapter(context,R.layout.list_item,albumesNames))
                onItemClickListener =this@TrackToAlbumFragment
            }
        })

        setupButton()
        setupDuration(view)




    }


    private fun setupButton(){
        binding.buttonSaveTrack.setOnClickListener{ saveTrackToAlbum()}
    }


    private fun setupDuration(view: View){

        val minutes = resources.getStringArray(R.array.minutes_array)
        val adapterMinutes = ArrayAdapter(
            view.context,
            R.layout.list_item,
            minutes
        )
        with(binding.autoCompleteTextViewMinutes){
            setAdapter(adapterMinutes)
        }


        val seconds = resources.getStringArray(R.array.seconds_array)
        val adapterSeconds = ArrayAdapter(
            view.context,
            R.layout.list_item,
            seconds
        )
        with(binding.autoCompleteTextViewSeconds){
            setAdapter(adapterSeconds)
        }

    }

    private fun validateForm(): Boolean {
        var isValid = true
        binding.tilTrackName.error= ""
        binding.tilTrackAlbum.error=""
        binding.tilTrackAlbumMinutes.error=""
        binding.tilTrackAlbumSeconds.error=""
        with(binding){
            if(trackName.text.toString().isBlank()){
                isValid = false
                tilTrackName.error = getString(R.string.form_required_field)
            }
            if(autoCompleteTextViewMinutes.text.toString().isBlank()){
                isValid = false
                tilTrackAlbumMinutes.error = getString(R.string.form_required_field)
            }
            if(autoCompleteTextViewSeconds.text.toString().isBlank()){
                isValid = false
                tilTrackAlbumSeconds.error = getString(R.string.form_required_field)
            }

            if(autoCompleteTextViewAlbum.text.toString().isBlank()){
                isValid = false
                tilTrackAlbum.error = getString(R.string.form_required_field)
            }

        }
        return isValid
    }


    private fun clearForm() {
        binding.trackName.setText("")
        binding.autoCompleteTextViewMinutes.setText("")
        binding.autoCompleteTextViewSeconds.setText("")
        binding.autoCompleteTextViewAlbum.setText("")
        positionAlbumSelected=-1
    }

    private fun saveTrackToAlbum() {
        if(validateForm()) {

            val dataStr = "Name: ${binding.trackName.text.toString()}," +
                    "Duración: ${binding.autoCompleteTextViewMinutes.text.toString()}:${binding.autoCompleteTextViewSeconds.text.toString()}," +
                    "Album: ${
                        albums.get(positionAlbumSelected).albumId.toString().plus("-")
                            .plus(albums.get(positionAlbumSelected).name)
                    }"

            Log.println(Log.INFO,"Informacion Track",dataStr)
            viewModel.addTrackToAlbum(binding.trackName.text.toString(),"${binding.autoCompleteTextViewMinutes.text.toString()}:${binding.autoCompleteTextViewSeconds.text.toString()}",albums.get(positionAlbumSelected).albumId)

            viewModel.track.observe(viewLifecycleOwner, Observer<Track> { t ->
                    track = t
                Log.println(Log.INFO,"Informacion Track",track.trackId.toString())
                Toast.makeText(activity, "Asociación Exitosa: Id Track:".plus(track.trackId.toString()), Toast.LENGTH_LONG).show()
                clearForm()
            })

            viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
                if(isNetworkError) onNetworkError()
            })

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onNetworkError(){
        if(!viewModel.isNetworkErrorShown.value!!){
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        positionAlbumSelected= position
    }

}