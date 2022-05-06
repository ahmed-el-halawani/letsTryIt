package com.newcore.letstryit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.newcore.letstryit.databinding.ActivityMainBinding
import com.newcore.letstryit.ui.home.HomeFragmentDirections

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            binding.navHost.findNavController().navigate(
                HomeFragmentDirections.actionGlobalHomeFragment()
            )
        }

    }


    fun toggleLoading(isLoading: Boolean) {
        binding.includeProgress.progressBar.isVisible = isLoading
    }

    fun isLoading(): Boolean = binding.includeProgress.progressBar.isVisible
}