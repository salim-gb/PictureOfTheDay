package com.example.pictureoftheday.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.FavoriteFragmentBinding

class Favorites : Fragment(R.layout.favorite_fragment) {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FavoriteFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}