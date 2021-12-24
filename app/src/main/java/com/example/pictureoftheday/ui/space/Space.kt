package com.example.pictureoftheday.ui.space

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.SpaceFragmentBinding
import com.example.pictureoftheday.util.ScreenSlidePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class Space : Fragment(R.layout.space_fragment) {

    private var _binding: SpaceFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ScreenSlidePagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SpaceFragmentBinding.bind(view)

        adapter = ScreenSlidePagerAdapter(this)
        binding.spacePager.adapter = adapter

        TabLayoutMediator(binding.spaceTabLayout, binding.spacePager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Earth"
                }
                1 -> {
                    tab.text = "Mars"
                }
                2 -> {
                    tab.text = "Moon"
                }
                3 -> {
                    tab.text = "Space Weather"
                }
                4 -> {
                    tab.text = "Meteorite"
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}