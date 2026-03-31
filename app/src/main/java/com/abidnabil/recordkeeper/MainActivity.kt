package com.abidnabil.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.abidnabil.recordkeeper.cycling.CyclingFragment
import com.abidnabil.recordkeeper.databinding.ActivityMainBinding
import com.abidnabil.recordkeeper.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNav.setOnItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuOptionCaptured = when (item.itemId) {
            R.id.reset_cycling -> {
                showConfirmationDiaLog("cycling", false)
                true

            }

            R.id.reset_running -> {
                showConfirmationDiaLog("running", false)
                true
            }

            R.id.reset_all -> {
                showConfirmationDiaLog("", true)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }

        }

        return menuOptionCaptured

    }

    private fun showConfirmationDiaLog(selection: String, isAll: Boolean) {
        AlertDialog.Builder(this).setTitle("Warning")
            .setMessage("You are about to delete all $selection records. Are you sure?")
            .setPositiveButton(
                "Yes"
            ) { _, _ ->
                if (isAll) {
                    getSharedPreferences(CyclingFragment.FILENAME, Context.MODE_PRIVATE).edit {
                        clear()

                    }
                    getSharedPreferences(RunningFragment.FILENAME, Context.MODE_PRIVATE).edit {
                        clear()
                    }
                } else {
                    getSharedPreferences(selection + "_records", MODE_PRIVATE).edit {
                        clear()
                    }

                }
                showConfirmation()

            }.setNegativeButton("No", { dialogue, _ ->
                dialogue.dismiss()
            }).show()


    }

    private fun showConfirmation() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
            else -> {}
        }

        val snackbar = Snackbar.make(
            binding.frameContent,
            "Records Cleared Successfully",
            Snackbar.LENGTH_LONG
        )
        snackbar.anchorView = binding.bottomNav
        snackbar.show()
    }


    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(p0: MenuItem) = when (p0.itemId) {
        R.id.nav_cycling -> {
            onCyclingClicked()
        }

        R.id.nav_running -> {
            onRunningClicked()
        }

        else -> false
    }

}
