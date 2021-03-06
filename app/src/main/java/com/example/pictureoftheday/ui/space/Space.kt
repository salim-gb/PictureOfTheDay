package com.example.pictureoftheday.ui.space

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import coil.load
import coil.transform.CircleCropTransformation
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.SpaceFragmentBinding
import com.example.pictureoftheday.util.DatePicker
import com.example.pictureoftheday.util.ScreenSlidePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class Space : Fragment(R.layout.space_fragment) {

    private var _binding: SpaceFragmentBinding? = null
    private val binding get() = _binding!!

    private var pagerPosition = 0

    private lateinit var adapter: ScreenSlidePagerAdapter

    private val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            pagerPosition = position
            when (position) {
                0, 3, 4 -> binding.spaceAppBar.elevation = 0f
                else -> super.onPageSelected(position)
            }
        }
    }

    private val viewModel: SpaceSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SpaceFragmentBinding.bind(view)

        binding.circleImageAppbar.load(R.drawable.space_astronaut) {
            transformations(CircleCropTransformation())
        }

        adapter = ScreenSlidePagerAdapter(this)
        binding.spacePager.adapter = adapter

        binding.spacePager.registerOnPageChangeCallback(callback)

        TabLayoutMediator(binding.spaceTabLayout, binding.spacePager) { tab, position ->
            when (position) {
                0 -> tab.text = "Earth"
                1 -> tab.text = "Mars"
                2 -> tab.text = "Moon"
                3 -> tab.text = "Space Weather"
                4 -> tab.text = "Meteorite"
            }
        }.attach()

        binding.spaceToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.calendar -> {
                    DatePicker.datePicker.show(parentFragmentManager, "date picker")
                    true
                }
                else -> false
            }
        }

        DatePicker.datePicker.addOnPositiveButtonClickListener {
            onCalendarDateChange(it)
        }

        viewModel.appBarElevationState.observe(viewLifecycleOwner) {
            binding.spaceAppBar.elevation = if (it) 20f else 0f
        }
    }

    private fun onCalendarDateChange(date: Long) {
        viewModel.onDateChange(
            date,
            pagerPosition
        )
    }

    override fun onDestroyView() {
        binding.spacePager.unregisterOnPageChangeCallback(callback)
        _binding = null
        super.onDestroyView()
    }
}