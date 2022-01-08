package com.example.pictureoftheday.ui.space.mars

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DiffUtil
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.MarsFragmentBinding
import com.example.pictureoftheday.model.ListItem
import com.example.pictureoftheday.model.MarsPhoto
import com.example.pictureoftheday.model.MarsPictureData
import com.example.pictureoftheday.ui.FullscreenImageFragmentDirections
import com.example.pictureoftheday.ui.space.SpaceSharedViewModel
import com.example.pictureoftheday.util.AdapterDelegates
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.CommonCallbackImpl
import com.example.pictureoftheday.util.Constants.Companion.DEFAULT_MARS_DATE
import com.google.android.material.snackbar.Snackbar

class Mars : Fragment(R.layout.mars_fragment) {

    private var _binding: MarsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpaceSharedViewModel by activityViewModels()

    var items = emptyList<ListItem>()
        set(value) {
            val callback = CommonCallbackImpl(
                oldItems = field,
                newItems = value,
            )
            field = value

            val diffResult = DiffUtil.calculateDiff(callback)
            adapter.currentList = field
            diffResult.dispatchUpdatesTo(adapter)
        }

    private val adapter = AdapterDelegates(
        delegates = listOf(MarsPhotoDelegate())
    ) {
        FullscreenImageFragmentDirections
        val action =
            FullscreenImageFragmentDirections.actionGlobalFullscreenImage(marsData = it as MarsPhoto)
        findNavController().navigate(
            action,
            navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_right
                    popEnter = R.anim.fade_in
                    popExit = R.anim.fade_out
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MarsFragmentBinding.bind(view)

        binding.marsRecyclerView.adapter = adapter

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
                    items = state.data.photos as List<MarsPhoto>
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