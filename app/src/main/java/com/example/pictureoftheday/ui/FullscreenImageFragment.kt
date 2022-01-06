package com.example.pictureoftheday.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.FullscreenImageFragmentBinding
import java.util.concurrent.TimeUnit

class FullscreenImageFragment : Fragment(R.layout.fullscreen_image_fragment) {

    private var _binding: FullscreenImageFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: FullscreenImageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FullscreenImageFragmentBinding.bind(view)

        postponeEnterTransition(50, TimeUnit.MILLISECONDS)

        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.fullscreenToolbar.setupWithNavController(navController, appBarConfiguration)

        /* set toolbar navigation icon */
        binding.fullscreenToolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationIconTint(ContextCompat.getColor(context, R.color.white))
        }

        args.homeData?.let {
            binding.imageView.load(it.url)
            binding.title.text = it.title
            binding.description.text = it.explanation
        }

        args.earthData?.let {
            binding.imageView.load(it.url)
            binding.title.text = it.dataset?.dataSet
            binding.description.text = it.date
        }

        args.marsData?.let {
            binding.imageView.load(it.url)
            binding.title.text = it.camera?.fullName
            binding.description.text = it.rover?.name
        }

        args.moonData?.let {
            binding.imageView.load(it.moonPicture)
            binding.title.text = it.title
            binding.description.text = it.abstractDes
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}