package com.example.pictureoftheday.ui.space.mars

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.MarsFragmentBinding
import com.example.pictureoftheday.model.MarsPictureData
import com.example.pictureoftheday.util.AppState
import com.example.pictureoftheday.util.DateHelperImpl
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

const val DEFAULT_DATE = "2018-06-03"
class Mars : Fragment(R.layout.mars_fragment) {

    private var _binding: MarsFragmentBinding? = null
    private val binding get() = _binding!!

    private val constraintsBuilder =
        CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())

    private var datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select Date")
        .setCalendarConstraints(constraintsBuilder.build())
        .build()

    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
    }

    private lateinit var marsAdapter: MarsAdapter

    private val dateHelperImpl = DateHelperImpl()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MarsFragmentBinding.bind(view)

        marsAdapter = MarsAdapter()

        binding.marsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = marsAdapter
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.sendRequestToServer(DEFAULT_DATE)

        binding.datePickerMarsPicture.setOnClickListener {
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

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Error -> {
                Snackbar.make(requireView(), "${state.error.message}", Snackbar.LENGTH_SHORT)
                    .show()
            }

            AppState.Loading -> {}

            is AppState.Success -> {
                if (state.data is MarsPictureData) {
                    marsAdapter.submitList(state.data.photos)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = Mars()
    }
}