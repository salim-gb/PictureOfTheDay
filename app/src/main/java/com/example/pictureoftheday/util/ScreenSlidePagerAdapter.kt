package com.example.pictureoftheday.util

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pictureoftheday.ui.space.Meteorites
import com.example.pictureoftheday.ui.space.Moon
import com.example.pictureoftheday.ui.space.Weather
import com.example.pictureoftheday.ui.space.earth.Earth
import com.example.pictureoftheday.ui.space.mars.Mars

private const val NUM_PAGES = 5

class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Earth.newInstance()
            1 -> Mars.newInstance()
            2 -> Moon.newInstance()
            3 -> Weather.newInstance()
            4 -> Meteorites.newInstance()
            else -> Earth.newInstance()
        }
    }
}

