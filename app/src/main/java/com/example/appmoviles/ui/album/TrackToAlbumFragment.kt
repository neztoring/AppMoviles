package com.example.appmoviles.ui.album

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appmoviles.R
import com.example.appmoviles.viewmodels.TrackToAlbumViewModel

class TrackToAlbumFragment : Fragment() {

    companion object {
        fun newInstance() = TrackToAlbumFragment()
    }

    private lateinit var viewModel: TrackToAlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrackToAlbumViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_track_to_album, container, false)
    }

}