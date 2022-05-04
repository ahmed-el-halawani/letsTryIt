package com.newcore.letstryit.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.newcore.letstryit.R
import com.newcore.letstryit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }


    fun toggleLoading(isLoading: Boolean){
        binding.includeProgress.progressBar.isVisible = isLoading
    }

    fun isLoading():Boolean = binding.includeProgress.progressBar.isVisible
}