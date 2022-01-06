package com.example.pictureoftheday.ui.home

import android.animation.*
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.HomeFragmentBinding
import com.example.pictureoftheday.model.PictureOfTheDayResponseData
import com.example.pictureoftheday.ui.FullscreenImageFragmentDirections
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.CoilHelper
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class Home : Fragment(R.layout.home_fragment) {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val constraintsBuilder =
        CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())

    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select Date")
        .setCalendarConstraints(constraintsBuilder.build())
        .build()

    private var animateToChip: Chip? = null

    private var currentData: PictureOfTheDayResponseData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.customImageView.setOnClickListener {
            currentData?.let {
                val extras = FragmentNavigatorExtras(binding.customImageView to "image_big")
                val action =
                    FullscreenImageFragmentDirections.actionGlobalFullscreenImage(homeData = currentData)
                findNavController().navigate(action, extras)
            }
        }

        binding.chipGroup.apply {
            when (viewModel.selectedDay.value) {
                0 -> {
                    check(R.id.chipToday)
                }
                -1 -> {
                    check(R.id.chipYesterday)
                }
                -2 -> {
                    check(R.id.chipBeforeYesterday)
                }
                else -> {
                    clearCheck()
                }
            }
        }

        binding.datePickerHome.setOnClickListener {
            datePicker.show(parentFragmentManager, "date picker")
        }

        datePicker.addOnPositiveButtonClickListener {
            onDateChange(it)
            binding.chipGroup.clearCheck()
        }

        // Handle click selected chip from chipGroup
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chipToday -> {
                    animateChip(binding.chipToday)
                    onDateChange(0)
                }
                R.id.chipYesterday -> {
                    animateChip(binding.chipYesterday)
                    onDateChange(-1)
                }
                R.id.chipBeforeYesterday -> {
                    animateChip(binding.chipBeforeYesterday)
                    onDateChange(-2)
                }
            }
        }

        binding.chipHdRes.setOnCheckedChangeListener { _, isChecked ->
            viewModel.liveData.value?.let {
                if (it is AppState.Success) {
                    if (it.data is PictureOfTheDayResponseData) {
                        CoilHelper.loadWithCoil(
                            binding.customImageView,
                            when (isChecked) {
                                true -> {
                                    animateChip(binding.chipHdRes)
                                    it.data.hdurl
                                }
                                false -> it.data.url
                            },
                            binding.loadingProgressBar
                        )
                    }
                }
            }
        }

        // Wikipedia search
        binding.wikitInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.wikiInputText.text.toString()}")
            })
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingProgressBar.visibility = View.GONE
                Snackbar.make(requireView(), "${appState.error.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
            AppState.Loading -> {
                binding.loadingProgressBar.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingProgressBar.visibility = View.GONE
                if (appState.data is PictureOfTheDayResponseData) {
                    showDetails(appState.data)
                }
            }
        }
    }

    private fun showDetails(data: PictureOfTheDayResponseData) {
        currentData = data
        with(binding) {
            title.text = data.title
            date.text = data.date
            CoilHelper.loadWithCoil(
                customImageView,
                when (chipHdRes.isChecked) {
                    true -> data.hdurl
                    false -> data.url
                },
                loadingProgressBar
            )

            description.text = data.explanation
            copyRight.text = data.copyright
        }
    }

    private fun animateChip(chip: Chip) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.12f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.12f)

        val animator = ObjectAnimator.ofPropertyValuesHolder(chip, scaleX, scaleY)
        animator.repeatCount = 5
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun moonShow() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2f)

        val scaler = ObjectAnimator.ofPropertyValuesHolder(binding.moon, scaleX, scaleY)

        val mover = ObjectAnimator.ofFloat(
            binding.moon,
            View.TRANSLATION_X, (100..1000).random().toFloat()
        )

        val set = AnimatorSet()

        set.playTogether(mover, scaler)
        set.duration = Random.nextLong(2000, 10000)
        set.start()
    }

    private fun starShow() {

        val c = binding.nasaLogo.parent as ViewGroup

        val containerW = c.width
        val containerH = c.height

        var starW: Float = binding.nasaLogo.width.toFloat()
        var starH: Float = binding.nasaLogo.height.toFloat()

        val newStar = AppCompatImageView(requireContext())

        newStar.setImageResource(R.drawable.ic_baseline_star_24)

        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        c.addView(newStar)

        newStar.scaleX = Random.nextFloat()

        newStar.scaleY = newStar.scaleX

        starW *= newStar.scaleX
        starH *= newStar.scaleY

        newStar.translationX = Math.random().toFloat() * containerW - starW / 2

        val mover = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y, -starH, containerH + starH)

        val lighter = ObjectAnimator.ofArgb(newStar, "colorFilter", Color.BLACK, Color.WHITE)

        val set = AnimatorSet()

        set.playTogether(mover, lighter)

        set.duration = Random.nextLong(1000, 10000)

        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                c.removeView(newStar)
            }
        })
        set.start()
    }

    private fun onDateChange(date: Long) {
        viewModel.onDateChange(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Home()
    }
}