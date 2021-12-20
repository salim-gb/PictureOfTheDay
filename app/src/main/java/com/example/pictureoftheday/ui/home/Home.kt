package com.example.pictureoftheday.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.HomeFragmentBinding
import com.example.pictureoftheday.repository.PictureOfTheDayResponseData
import com.example.pictureoftheday.util.AppState
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class Home : Fragment(R.layout.home_fragment) {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

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

    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val day = c.get(Calendar.DAY_OF_MONTH)

    private val today = "$year-${month + 1}-$day"
    private val yesterday = "$year-${month + 1}-${day - 1}"
    private val beforeYesterday = "$year-${month + 1}-${day - 2}"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.sendServerRequest(today)

        binding.homeToolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.selectDate -> {
                    datePicker.show(parentFragmentManager, "date picker")
                    true
                }
                else -> false
            }
        }

        datePicker.addOnPositiveButtonClickListener {
            val dateSelected = datePicker.selection
            Timber.d("positive button date picker: ${getCurrentDate(dateSelected)}")
            viewModel.sendServerRequest(getCurrentDate(dateSelected))
            binding.chipGroup.clearCheck()
        }

        // Handle click selected chip from chipGroup
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.chipToday -> {
                    viewModel.sendServerRequest(today)
                }
                R.id.chipYesterday -> {
                    viewModel.sendServerRequest(yesterday)
                }
                R.id.chipBeforeYesterday -> {
                    viewModel.sendServerRequest(beforeYesterday)
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
                binding.progressBar.visibility = View.GONE
                Snackbar.make(requireView(), "${appState.error.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
            AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.progressBar.visibility = View.GONE
                showDetails(appState.pictureOfTheDayResponseData)
            }
        }
    }

    private fun showDetails(pictureOfTheDay: PictureOfTheDayResponseData) {
        binding.title.text = pictureOfTheDay.title
        binding.date.text = pictureOfTheDay.date
        binding.customImageView.load(pictureOfTheDay.url) {
            crossfade(true)
        }
        binding.description.text = pictureOfTheDay.explanation
        binding.copyRight.text = pictureOfTheDay.copyright
    }

    private fun getCurrentDate(date: Long?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return sdf.format(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Home()
    }
}