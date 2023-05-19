package com.example.appmoviles.ui.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View.OnTouchListener
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
import com.example.appmoviles.ui.performer.PerformerDetailActivity


class PerformersAdapter(private val isFavoriteView: Boolean) :
    RecyclerView.Adapter<PerformersAdapter.PerformerViewHolder>() {


    var performers: List<Performer> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val withDataBinding: PerformerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PerformerViewHolder.LAYOUT,
            parent,
            false
        )
        return PerformerViewHolder(withDataBinding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        var isSelected = false
        val favoriteUnselected = R.drawable.baseline_star_border_24
        val favoriteSelected = R.drawable.baseline_star_24
        var icon = favoriteUnselected
        holder.viewDataBinding.also {
            it.performer = performers[position]
            Glide.with(holder.itemView)
                .load(performers[position].image).apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image
                ))
                .into(holder.viewDataBinding.performerImage)
        }
        holder.viewDataBinding.root.setOnClickListener {v ->
            val intent = Intent(v.context, PerformerDetailActivity::class.java)
            intent.putExtra("performerDetail", performers[position])
            v.context.startActivity(intent)
        }

        holder.viewDataBinding.performerName.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (event.rawX >= holder.viewDataBinding.performerName.right - holder.viewDataBinding.performerName.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                ) {
                    isSelected = !isSelected
                    icon = if (isSelected) favoriteSelected else favoriteUnselected
                    holder.viewDataBinding.performerName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        icon,
                        0
                    )

                    return@OnTouchListener true
                }
            }
            false
        })
        if (isFavoriteView) {
            holder.viewDataBinding.performerName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0,
                0,
                icon,
                0
            )
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
