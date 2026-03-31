package com.abidnabil.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abidnabil.recordkeeper.EditRecordActivity
import com.abidnabil.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment : Fragment() {
    private lateinit var binding: FragmentCyclingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences =
            requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        binding.textViewLongestRideValue.text =
            runningPreferences.getString("Longest Ride record", null)
        binding.textViewLongestRideDate.text =
            runningPreferences.getString("Longest Ride date", null)
        binding.textViewBiggestClimbValue.text =
            runningPreferences.getString("Biggest Climb record", null)
        binding.textViewBiggestClimbDate.text =
            runningPreferences.getString("Biggest Climb date", null)
        binding.textViewBestAverageSpeedValue.text =
            runningPreferences.getString("Best Average Speed record", null)
        binding.textViewBestAverageSpeedDate.text =
            runningPreferences.getString("Best Average Speed date", null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.containerLongestRide.setOnClickListener {
            launchCyclingRecordScreen("Longest Ride", "Distance")

        }
        binding.containerBiggestClimb.setOnClickListener {
            launchCyclingRecordScreen("Biggest Climb", "Height")
        }
        binding.containerBestAverageSpeed.setOnClickListener {
            launchCyclingRecordScreen("Best Average Speed", "Average Speed")
        }
    }

    private fun launchCyclingRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(
            "screen_data",
            EditRecordActivity.ScreenData(record, FILENAME, recordFieldHint)
        )
        startActivity(intent)

    }

    companion object {
        const val FILENAME="cycling_records"
    }
}