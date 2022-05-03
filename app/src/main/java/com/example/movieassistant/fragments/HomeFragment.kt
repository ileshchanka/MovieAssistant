package com.example.movieassistant.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.movieassistant.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction().replace(R.id.home_container, MoviesFragment()).commit()
        val bottomMenu = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_list -> {
                    childFragmentManager.beginTransaction().replace(R.id.home_container, MoviesFragment()).commit()
                    true
                }
                R.id.menu_favourites -> {
                    childFragmentManager.beginTransaction().replace(R.id.home_container, FavouriteFragment()).commit()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}
