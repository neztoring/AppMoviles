package com.example.appmoviles.ui.performer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmoviles.R
import com.example.appmoviles.databinding.PerformerFragmentBinding
import com.example.appmoviles.models.Performer
import com.example.appmoviles.ui.adapters.PerformersAdapter
import com.example.appmoviles.viewmodels.PerformerViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PerformerFragment : Fragment() {
    private var _binding: PerformerFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PerformerViewModel
    private var viewModelAdapter: PerformersAdapter? = null
    private var isFavoriteView = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isFavoriteView = requireActivity().intent.extras!!.getBoolean("favorites")
        _binding = PerformerFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = PerformersAdapter(isFavoriteView, ::addFavoritePerformer, ::removeFavoritePerformer)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.fragmentsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.artistas)
        viewModel = ViewModelProvider(this, PerformerViewModel.Factory(activity.application)).get(PerformerViewModel::class.java)
        viewModel.performers.observe(viewLifecycleOwner, Observer<List<Performer>> {
            it.apply {
                viewModelAdapter!!.performers = this
            }
        })
        viewModel.favoritePerformers.observe(viewLifecycleOwner, Observer<List<Performer>> {
            it.apply {
                viewModelAdapter!!.favoritePerformers = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    fun addFavoritePerformer(performerId: Int) {
        viewModel.addFavoritePerformer(100, performerId)

        viewModel.performersAdded.observe(this, Observer<Any> { t ->
            Toast.makeText(requireActivity(), "Agregado a favoritos!", Toast.LENGTH_LONG).show()
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if(isNetworkError)  Toast.makeText(requireActivity(), "No es posible agregar a favoritos!", Toast.LENGTH_LONG).show()
        })
    }

    fun removeFavoritePerformer(performerId: Int) {
        viewModel.removeFavoritePerformer(100, performerId)

        viewModel.performersAdded.observe(this, Observer<Any> { t ->
            Toast.makeText(requireActivity(), "Se removio de favoritos!", Toast.LENGTH_SHORT).show()
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if(isNetworkError)  Toast.makeText(requireActivity(), "No es posible remover de favoritos!", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}