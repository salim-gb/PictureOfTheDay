package com.example.pictureoftheday.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.HomeFragmentBinding
import com.example.pictureoftheday.model.PictureOfTheDayResponseData
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.CoilHelper
import com.example.pictureoftheday.util.DateHelperImpl
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar

class Home : Fragment(R.layout.home_fragment) {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val dateHelperImpl = DateHelperImpl()

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private val constraintsBuilder =
        CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())

    private var datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select Date")
        .setCalendarConstraints(constraintsBuilder.build())
        .build()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)

        val observer = Observer<AppState> {
            renderData(it)
        }

        viewModel.getData().observe(this, observer)

        when (viewModel.selectedDate.value) {
            dateHelperImpl.today -> {
                binding.chipGroup.check(R.id.chipToday)
            }
            dateHelperImpl.yesterday -> {
                binding.chipGroup.check(R.id.chipYesterday)
            }
            dateHelperImpl.beforeYesterday -> {
                binding.chipGroup.check(R.id.chipBeforeYesterday)
            }
            else -> {
                binding.chipGroup.clearCheck()
            }
        }

//        binding.homeToolBar.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.selectDate -> {
//                    datePicker.show(parentFragmentManager, "date picker")
//                    true
//                }
//                else -> false
//            }
//        }

        datePicker.addOnPositiveButtonClickListener {
            dateHelperImpl.getFormatDate(it).also { formatDate ->
                onDateChange(formatDate)
            }
            binding.chipGroup.clearCheck()
        }

        // Handle click selected chip from chipGroup
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.chipToday -> {
                    onDateChange(dateHelperImpl.today)
                }
                R.id.chipYesterday -> {
                    onDateChange(dateHelperImpl.yesterday)
                }
                R.id.chipBeforeYesterday -> {
                    onDateChange(dateHelperImpl.beforeYesterday)
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
                            }
                        )
                    }
                }
            }
        }

        // Wikipedia search
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputWikiText.text.toString()}")
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
                }
            )

            description.text = data.explanation
            copyRight.text = data.copyright
        }
    }

    private fun onDateChange(date: String) {
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