package com.example.pictureoftheday.ui.space.mars

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.MarsFragmentBinding
import com.example.pictureoftheday.model.MarsResponseData
import com.example.pictureoftheday.util.AppState
import com.google.android.material.snackbar.Snackbar

class Mars : Fragment(R.layout.mars_fragment) {

    private var _binding: MarsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MarsFragmentBinding.bind(view)

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
                if (appState.data is MarsResponseData) {
                    binding.marsImageView.load(appState.data.url)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Mars()
    }
}