package com.example.pictureoftheday.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.FullscreenImageFragmentBinding

class FullscreenImageFragment: Fragment(R.layout.fullscreen_image_fragment) {

    private var _binding: FullscreenImageFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: FullscreenImageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FullscreenImageFragmentBinding.bind(view)

        val navController = findNavController()
        binding.fullscreenToolbar.setupWithNavController(navController)

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