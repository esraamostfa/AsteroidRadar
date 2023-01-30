package com.esraa.egfwd.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esraa.egfwd.asteroidradar.data.models.Asteroid
import com.esraa.egfwd.asteroidradar.databinding.AsteroidItemBinding

class AsteroidRecyclerViewAdepter: ListAdapter<Asteroid, AsteroidRecyclerViewAdepter.ViewHolder>(AsteroidDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AsteroidItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: AsteroidItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (asteroid: Asteroid) {
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }
    }

    companion object AsteroidDiffCallBack : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

}