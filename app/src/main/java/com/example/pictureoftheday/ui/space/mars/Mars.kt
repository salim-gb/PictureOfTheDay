package com.example.pictureoftheday.ui.space.mars

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.MarsFragmentBinding
import com.example.pictureoftheday.model.MarsPictureData
import com.example.pictureoftheday.ui.FullscreenImageFragmentDirections
import com.example.pictureoftheday.ui.space.SpaceSharedViewModel
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.Constants.Companion.DEFAULT_MARS_DATE
import com.google.android.material.snackbar.Snackbar

class Mars : Fragment(R.layout.mars_fragment) {

    private var _binding: MarsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var marsAdapter: MarsAdapter

    private val viewModel: SpaceSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MarsFragmentBinding.bind(view)

        marsAdapter = MarsAdapter {
            FullscreenImageFragmentDirections
            val action =
                FullscreenImageFragmentDirections.actionGlobalFullscreenImage(marsData = it)
            findNavController().navigate(action)
        }

        binding.marsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = marsAdapter
        }

        viewModel.getMarsData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.sendMarsRequestToServer(DEFAULT_MARS_DATE)

        binding.marsRecyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            onScrollChange()
        }
    }

    private fun onScrollChange() {
        viewModel.onScrollStateChange(binding.marsRecyclerView.canScrollVertically(-1))
    }


    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Error -> {
                Snackbar.make(requireView(), "${state.error.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }

            AppState.Loading -> {}

            is AppState.Success -> {
                if (state.data is MarsPictureData) {
                    marsAdapter.submitList(state.data.photos)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onScrollStateChange(binding.marsRecyclerView.canScrollVertically(-1))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Mars()
    }
}