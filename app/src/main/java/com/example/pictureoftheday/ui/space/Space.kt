package com.example.pictureoftheday.ui.space

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    private lateinit var adapter: ScreenSlidePagerAdapter

    private val viewModel: SpaceSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SpaceFragmentBinding.bind(view)

        binding.circleImageAppbar.load(R.drawable.space_astronaut) {
            transformations(CircleCropTransformation())
        }

        adapter = ScreenSlidePagerAdapter(this)
        binding.spacePager.adapter = adapter

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

        viewModel.canScrollVertically.observe(viewLifecycleOwner) {
            binding.spaceTabLayout.isSelected = it
        }
    }

    private fun onCalendarDateChange(date: Long) {
        viewModel.onDateChange(date, binding.spacePager.currentItem)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}