package com.esraa.egfwd.asteroidradar.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.esraa.egfwd.asteroidradar.AsteroidApplication
import com.esraa.egfwd.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val application: AsteroidApplication by lazy {
        activity?.application as AsteroidApplication
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this,
            MainViewModelFactory(application.repository))[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = AsteroidRecyclerViewAdepter()
        binding.asteroidRecycler.adapter = adapter

        return binding.root

        }

}