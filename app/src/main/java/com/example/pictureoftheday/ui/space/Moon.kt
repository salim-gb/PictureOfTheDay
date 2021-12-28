package com.example.pictureoftheday.ui.space

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.MoonFragmentBinding
import com.example.pictureoftheday.repository.MoonDataSource

class Moon : Fragment(R.layout.moon_fragment) {

    private var _binding: MoonFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MoonFragmentBinding.bind(view)

        val moonPicturesList = MoonDataSource.getMoonPicture()

        binding.moonRecyclerView.adapter = MoonAdapter(moonPicturesList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Moon()
    }
}