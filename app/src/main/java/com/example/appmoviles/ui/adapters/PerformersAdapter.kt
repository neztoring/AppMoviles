package com.example.appmoviles.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.appmoviles.R
import com.example.appmoviles.databinding.PerformerItemBinding
import com.example.appmoviles.models.Performer
import com.squareup.picasso.Picasso

class PerformersAdapter : RecyclerView.Adapter<PerformersAdapter.PerformerViewHolder>(){

    var performers :List<Performer> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val withDataBinding: PerformerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PerformerViewHolder.LAYOUT,
            parent,
            false)
        return PerformerViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.performer = performers[position]
            Glide.with(holder.itemView)
                .load(performers[position].image).apply(
                    RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(holder.viewDataBinding.performerImage)
            }
        holder.viewDataBinding.root.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return performers.size
    }

    class PerformerViewHolder(val viewDataBinding: PerformerItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.performer_item
        }

    }
}