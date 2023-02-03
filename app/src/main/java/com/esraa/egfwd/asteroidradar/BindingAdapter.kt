package com.esraa.egfwd.asteroidradar

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid
import com.esraa.egfwd.asteroidradar.ui.main.AsteroidRecyclerViewAdepter
import com.esraa.egfwd.asteroidradar.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Picasso.with(imgView.context)
            .load(imgUri)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.placeholder_picture_of_day)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DBAsteroid>?) {
    val adapter = recyclerView.adapter as AsteroidRecyclerViewAdepter
    adapter.submitList(data)
}

@BindingAdapter("hazardousImage")
fun bindHazardousIcon(image: ImageView, asteroid: DBAsteroid) {
    if (asteroid.isPotentiallyHazardous) {
        image.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        image.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("apiStatus")
fun ProgressBar.setVisibility(apiStatus: MainViewModel.APIStatus) {
    if(apiStatus== MainViewModel.APIStatus.LOADING) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}
