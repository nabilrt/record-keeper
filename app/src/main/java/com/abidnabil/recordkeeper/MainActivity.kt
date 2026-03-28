package com.abidnabil.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.abidnabil.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.reset_cycling -> {
            Toast.makeText(this, "Clicked Reset Cycling Button", Toast.LENGTH_LONG).show()
            true
        }

        R.id.reset_running -> {
            Toast.makeText(this, "Clicked Reset Running Button", Toast.LENGTH_LONG).show()
            true
        }

        R.id.reset_all -> {
            Toast.makeText(this, "Clicked Reset All Button", Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }


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
