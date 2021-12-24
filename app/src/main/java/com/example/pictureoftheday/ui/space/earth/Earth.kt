package com.example.pictureoftheday.ui.space.earth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.EarthFragmentBinding
import com.example.pictureoftheday.model.EarthResponseData
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.CoilHelper
import com.example.pictureoftheday.util.Constants.Companion.EMPTY_REQUEST
import com.example.pictureoftheday.util.DateHelperImpl
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class Earth : Fragment(R.layout.earth_fragment) {

    private var _binding: EarthFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EarthViewModel by lazy {
        ViewModelProvider(this).get(EarthViewModel::class.java)
    }

    private val constraintsBuilder =
        CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())

    private var datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select Date")
        .setCalendarConstraints(constraintsBuilder.build())
        .build()

    private val dateHelperImpl = DateHelperImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("earth onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("earth onCreateView called")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("earth onViewCreated called")
        _binding = EarthFragmentBinding.bind(view)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.isChipChecked.observe(viewLifecycleOwner) { isChecked ->
            if (!isChecked) {
                binding.chipEarthPictureToday.isChecked = false
            }
        }

        binding.chipEarthPictureToday.setOnClickListener {
            with(binding.chipEarthPictureToday) {
                if (!isChecked) {
                    this.isChecked = true
                } else {
                    onChipEarthPictureToday()
                }
            }
        }

        binding.datePickerEarthPicture.setOnClickListener {
            datePicker.show(parentFragmentManager, "date picker earth")
        }

        datePicker.addOnPositiveButtonClickListener {
            dateHelperImpl.getFormatDate(it).also { date ->
                onDateChange(date)
            }
        }
    }

    private fun onDateChange(date: String) {
        viewModel.onDateChange(date)
    }

    private fun onChipEarthPictureToday() {
        viewModel.onChipEarthPictureToday()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Snackbar.make(requireView(), "${appState.error.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }
            AppState.Loading -> {}
            is AppState.Success -> {
                when (appState.data) {
                    is EarthResponseData -> {

                        binding.noPictureMessage.visibility = View.GONE

                        CoilHelper.loadWithCoil(
                            binding.earthImageView,
                            appState.data.url,
                            binding.loadingProgressBar
                        )
                    }
                    EMPTY_REQUEST -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        binding.noPictureMessage.visibility = View.VISIBLE
                        binding.noPictureMessage.text = getString(R.string.no_picture_message)
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("earth onDestroyView called")
        _binding = null
    }

    companion object {
        fun newInstance() = Earth()
    }
}