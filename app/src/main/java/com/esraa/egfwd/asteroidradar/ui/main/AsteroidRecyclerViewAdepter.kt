package com.esraa.egfwd.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid
import com.esraa.egfwd.asteroidradar.databinding.AsteroidItemBinding
import com.esraa.egfwd.asteroidradar.databinding.AsteroidItemBindingImpl

class AsteroidRecyclerViewAdepter(private val clickListener: AsteroidClickListener): ListAdapter<DBAsteroid, AsteroidRecyclerViewAdepter.ViewHolder>(AsteroidDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AsteroidItemBindingImpl.inflate(inflater,  parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            clickListener.onClick(item)
        }
    }

    class ViewHolder(private val binding: AsteroidItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (asteroid: DBAsteroid) {
            binding.asteroid = asteroid
            binding.executePendingBindings()
        }
    }

    companion object AsteroidDiffCallBack : DiffUtil.ItemCallback<DBAsteroid>() {
        override fun areItemsTheSame(oldItem: DBAsteroid, newItem: DBAsteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DBAsteroid, newItem: DBAsteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class AsteroidClickListener(val clickListener: (asteroid: DBAsteroid) -> Unit) {
        fun onClick(asteroid: DBAsteroid) = clickListener(asteroid)
    }

}