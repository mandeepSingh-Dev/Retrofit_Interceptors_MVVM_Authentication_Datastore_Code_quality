package com.whitzapp.WallWhitz.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.whitzapp.WallWhitz.R
import com.whitzapp.WallWhitz.data.remote.model.Hit
import com.whitzapp.WallWhitz.databinding.ImageListItemBinding

class WallpapersAdapter : ListAdapter<Hit, WallpapersAdapter.WallpapersViewholderr>(diffUtils) {

    inner class WallpapersViewholderr(val binding : ImageListItemBinding) : ViewHolder(binding.root){
        fun bind(hit : Hit){

            binding.imageItem.load(hit.previewURL){
                crossfade(true)
                    placeholder(R.drawable.loadingimage)
               // transformations(CircleCropTransformation())

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersViewholderr {
        val binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return WallpapersViewholderr(binding)
    }

    override fun onBindViewHolder(holder: WallpapersViewholderr, position: Int) {
        val hit = getItem(position)
        holder.bind(hit)

    }

    object diffUtils : DiffUtil.ItemCallback<Hit>() {
        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }


}