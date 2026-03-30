package com.abidnabil.recordkeeper

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.abidnabil.recordkeeper.databinding.ActivityEditRecordBinding
import com.abidnabil.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRecordBinding
    private val recordTitle by lazy {
        intent.getStringExtra("title")
    }

    private val recordType by lazy {
        intent.getStringExtra("recordType")
    }
    private val runningRecords by lazy {
        getSharedPreferences("running_records", Context.MODE_PRIVATE)
    }

    private val cyclingRecords by lazy {
        getSharedPreferences("cycling_records", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInitialData(recordTitle, recordType)

        title = if (recordType == "cycling") "$recordTitle details" else "$recordTitle Record"


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
        if (recordType == "cycling") {
            cyclingRecords.edit {
                remove("$recordTitle record")
                remove("$recordTitle date")
            }
        } else {
            runningRecords.edit {
                remove("$recordTitle time")
                remove("$recordTitle date")
            }
        }

    }

    private fun getInitialData(recordTitle: String?, recordType: String?) {
        if (recordType == "cycling") {
            binding.editTextRecord.setText(
                cyclingRecords.getString("$recordTitle record", "").toString()
            )
            binding.editTextDate.setText(
                cyclingRecords.getString("$recordTitle date", "").toString()
            )
        } else {
            binding.editTextRecord.setText(
                runningRecords.getString("$recordTitle time", "").toString()
            )
            binding.editTextDate.setText(
                runningRecords.getString("$recordTitle date", "").toString()
            )
        }

    }

    private fun saveRecord() {
        if (recordType == "cycling") {
            cyclingRecords.edit {
                putString("$recordTitle record", binding.editTextRecord.text.toString())
                putString("$recordTitle date", binding.editTextDate.text.toString())
            }
        } else {
            runningRecords.edit {
                putString("$recordTitle time", binding.editTextRecord.text.toString())
                putString("$recordTitle date", binding.editTextDate.text.toString())
            }
        }

    }
}