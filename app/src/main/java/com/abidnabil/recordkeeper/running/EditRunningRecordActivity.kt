package com.abidnabil.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.abidnabil.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRunningRecordBinding
    private val distance by lazy {
        intent.getStringExtra("Distance")
    }
    private val runningRecords by lazy {
        getSharedPreferences("running_records", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInitialData(distance)

        title = "$distance Record"

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }

    }

    private fun clearRecord() {
        runningRecords.edit {
            remove("$distance time")
            remove("$distance date")
        }

    }

    private fun getInitialData(distance: String?) {
        binding.editTextRecord.setText(runningRecords.getString("$distance time", "").toString())
        binding.editTextDate.setText(runningRecords.getString("$distance date", "").toString())
    }

    private fun saveRecord() {
        runningRecords.edit {
            putString("$distance time", binding.editTextRecord.text.toString())
            putString("$distance date", binding.editTextDate.text.toString())
        }
    }
}