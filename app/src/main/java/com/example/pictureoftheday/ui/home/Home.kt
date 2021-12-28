package com.example.pictureoftheday.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.HomeFragmentBinding
import com.example.pictureoftheday.model.PictureOfTheDayResponseData
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.CoilHelper
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar

class Home : Fragment(R.layout.home_fragment) {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private val constraintsBuilder =
        CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())

    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select Date")
        .setCalendarConstraints(constraintsBuilder.build())
        .build()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
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
                    onDateChange(0)
                }
                R.id.chipYesterday -> {
                    onDateChange(-1)
                }
                R.id.chipBeforeYesterday -> {
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
                                true -> it.data.hdurl
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