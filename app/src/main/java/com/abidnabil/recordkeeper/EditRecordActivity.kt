package com.abidnabil.recordkeeper

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.abidnabil.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

class EditRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRecordBinding
    private val screenData by lazy {
       // intent.getSerializableExtra("screen_data") as ScreenData
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("screen_data", ScreenData::class.java) as ScreenData
        } else {
            intent.getSerializableExtra("screen_data") as ScreenData
        }
    }

    private val recordPreferences by lazy {
        getSharedPreferences(screenData.sharedPreferenceName, Context.MODE_PRIVATE)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInitialData()

        title = "${screenData.record} Details"
        binding.editTextRecord.hint = screenData.recordFieldHint

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

        recordPreferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }


    }

    private fun getInitialData() {

        binding.editTextRecord.setText(
            recordPreferences.getString("${screenData.record} record", "").toString()
        )
        binding.editTextDate.setText(
            recordPreferences.getString("${screenData.record} date", "").toString()
        )


    }

    private fun saveRecord() {

        recordPreferences.edit {
            putString("${screenData.record} record", binding.editTextRecord.text.toString())
            putString("${screenData.record} date", binding.editTextDate.text.toString())
        }


    }

    data class ScreenData(
        val record: String,
        val sharedPreferenceName: String,
        val recordFieldHint: String
    ) : Serializable
}