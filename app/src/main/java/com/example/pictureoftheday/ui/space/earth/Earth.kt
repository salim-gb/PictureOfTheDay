package com.example.pictureoftheday.ui.space.earth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.EarthFragmentBinding
import com.example.pictureoftheday.model.EarthResponseData
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.CoilHelper
import com.google.android.material.snackbar.Snackbar

class Earth : Fragment(R.layout.earth_fragment) {

    private var _binding: EarthFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EarthViewModel by lazy {
        ViewModelProvider(this).get(EarthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EarthFragmentBinding.bind(view)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Snackbar.make(requireView(), "${appState.error.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
            AppState.Loading -> {}
            is AppState.Success -> {
                if (appState.data is EarthResponseData) {
                    CoilHelper.loadWithCoil(
                        binding.earthImageView,
                        appState.data.url,
                        binding.loadingProgressBar,
                        binding.blurView
                    )
                    binding.earthImageView.load(appState.data.url)
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