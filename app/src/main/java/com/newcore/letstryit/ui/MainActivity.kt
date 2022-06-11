package com.newcore.letstryit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavHost
import androidx.navigation.ui.setupActionBarWithNavController
import com.newcore.letstryit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.navHost.id) as NavHost).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController)

        binding.button.setOnClickListener {
            navController.navigate(
                HomeFragmentDirections.actionGlobalHomeFragment()
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    fun toggleLoading(isLoading: Boolean) {
        binding.includeProgress.progressBar.isVisible = isLoading
    }

    fun isLoading(): Boolean = binding.includeProgress.progressBar.isVisible
}