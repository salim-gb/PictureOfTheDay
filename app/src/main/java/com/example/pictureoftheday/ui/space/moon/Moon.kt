package com.example.pictureoftheday.ui.space.moon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DiffUtil
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.MoonFragmentBinding
import com.example.pictureoftheday.model.MoonPicture
import com.example.pictureoftheday.repository.MoonDataSource
import com.example.pictureoftheday.ui.FullscreenImageFragmentDirections
import com.example.pictureoftheday.ui.space.SpaceSharedViewModel
import com.example.pictureoftheday.util.AdapterDelegates
import com.example.pictureoftheday.util.CommonCallbackImpl
import com.example.pictureoftheday.model.ListItem

class Moon : Fragment(R.layout.moon_fragment) {

    private var _binding: MoonFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpaceSharedViewModel by activityViewModels()

    private var items: List<ListItem> = emptyList()
        set(value) {
            val callback = CommonCallbackImpl(
                oldItems = field,
                newItems = value,
            )
            field = value
            adapter.currentList = items
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(adapter)
        }

    private val adapter = AdapterDelegates(listOf(MoonDelegate()), items) { listItem, _ ->
        onAdapterClick(listItem as MoonPicture)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MoonFragmentBinding.bind(view)

        val moonPicturesList = MoonDataSource.getMoonPicture()

        items = moonPicturesList

        binding.moonRecyclerView.adapter = adapter
        binding.moonRecyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            onScrollChange()
        }
    }

    private fun onAdapterClick(moonPicture: MoonPicture) {
        val action =
            FullscreenImageFragmentDirections.actionGlobalFullscreenImage(moonData = moonPicture)
        findNavController().navigate(
            action,
            navOptions {
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                    popEnter = android.R.animator.fade_in
                    popExit = android.R.animator.fade_out
                }
            })
    }

    private fun onScrollChange() {
        viewModel.onScrollStateChange(binding.moonRecyclerView.canScrollVertically(-1))
    }

    override fun onResume() {
        super.onResume()
        viewModel.onScrollStateChange(binding.moonRecyclerView.canScrollVertically(-1))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Moon()
    }
}