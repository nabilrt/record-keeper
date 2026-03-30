package com.abidnabil.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abidnabil.recordkeeper.EditRecordActivity
import com.abidnabil.recordkeeper.running.EditRunningRecordActivity
import com.abidnabil.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRunningBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        displayRecords()


    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences =
            requireContext().getSharedPreferences("running_records", Context.MODE_PRIVATE)
        binding.textView5kmValue.text = runningPreferences.getString("5km time", null)
        binding.textView5kmDate.text = runningPreferences.getString("5km date", null)
        binding.textView10kmValue.text = runningPreferences.getString("10km time", null)
        binding.textView10kmDate.text = runningPreferences.getString("10km date", null)
        binding.textViewHalfMarathonValue.text =
            runningPreferences.getString("Half-marathon time", null)
        binding.textViewHalfMarathonDate.text =
            runningPreferences.getString("Half-marathon date", null)
        binding.textViewMarathonValue.text = runningPreferences.getString("Marathon time", null)
        binding.textViewMarathonDate.text = runningPreferences.getString("Marathon date", null)
    }

    private fun setupClickListeners() {
        binding.container5km.setOnClickListener {
            launchRunningRecordScreen("5km")
        }

        binding.container10km.setOnClickListener {
            launchRunningRecordScreen("10km")
        }

        binding.containerHalfMarathon.setOnClickListener {
            launchRunningRecordScreen("Half-marathon")
        }

        binding.containerMarathon.setOnClickListener {
            launchRunningRecordScreen("Marathon")
        }

    }

    private fun launchRunningRecordScreen(title: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("recordType", "running")
        startActivity(intent)
    }

}