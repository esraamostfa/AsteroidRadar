package com.esraa.egfwd.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.esraa.egfwd.asteroidradar.AsteroidApplication
import com.esraa.egfwd.asteroidradar.R
import com.esraa.egfwd.asteroidradar.data.repository.AsteroidRepository
import com.esraa.egfwd.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment()  {

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


        setupMenu()
        return binding.root

        }


    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                viewModel.filterAsteroids(when(menuItem.itemId){
                    R.id.next_week_asteroids -> AsteroidRepository.AsteroidsFilter.NEXT_WEEK
                    R.id.today_asteroids -> AsteroidRepository.AsteroidsFilter.TODAY
                    R.id.saved_asteroids -> AsteroidRepository.AsteroidsFilter.SAVED
                    else -> AsteroidRepository.AsteroidsFilter.NEXT_WEEK
                })

                // Validate and handle the selected menu item
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

   }