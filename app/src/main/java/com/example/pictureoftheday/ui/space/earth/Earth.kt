package com.example.pictureoftheday.ui.space.earth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.EarthFragmentBinding
import com.example.pictureoftheday.model.EarthResponseData
import com.example.pictureoftheday.ui.space.SpaceSharedViewModel
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.CoilHelper
import com.example.pictureoftheday.util.Constants.Companion.DEFAULT_EARTH_DATE
import com.example.pictureoftheday.util.Constants.Companion.NOT_FOUND
import com.google.android.material.snackbar.Snackbar

class Earth : Fragment(R.layout.earth_fragment) {

    private var _binding: EarthFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpaceSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EarthFragmentBinding.bind(view)

        viewModel.getEarthData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.sendEarthRequestToServer(DEFAULT_EARTH_DATE)

        viewModel.isChipEarthPictureTodayChecked.observe(viewLifecycleOwner) { isChecked ->
            if (!isChecked) {
                binding.chipEarthPictureToday.isChecked = false
            }
        }

        binding.chipEarthPictureToday.setOnClickListener {
            with(binding.chipEarthPictureToday) {
                if (!isChecked) {
                    this.isChecked = true
                } else {
                    onChipEarthPictureToday()
                }
            }
        }
    }

    private fun onChipEarthPictureToday() {
        viewModel.onChipEarthPictureTodayClick()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Snackbar.make(requireView(), "${appState.error.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
            AppState.Loading -> {}
            is AppState.Success -> {
                when (appState.data) {
                    is EarthResponseData -> {
                        binding.noPictureMessage.visibility = View.GONE

                        CoilHelper.loadWithCoil(
                            binding.earthImageView,
                            appState.data.url,
                            binding.loadingProgressBar
                        )
                    }
                    NOT_FOUND -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        binding.noPictureMessage.visibility = View.VISIBLE
                        binding.noPictureMessage.text = getString(R.string.no_picture_message)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Earth()
    }
}