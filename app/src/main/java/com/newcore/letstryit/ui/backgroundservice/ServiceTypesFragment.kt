package com.newcore.letstryit.ui.backgroundservice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.core.util.adapters.ButtonsAdapter
import com.newcore.letstryit.core.util.adapters.ElevatedButton
import com.newcore.letstryit.databinding.FragmentButtonsAdapterBinding
import com.newcore.letstryit.services.ExampleJobService
import com.newcore.letstryit.services.ForegroundServiceExample

class ServiceTypesFragment :
    BaseFragment<FragmentButtonsAdapterBinding>(FragmentButtonsAdapterBinding::inflate) {

    private val vm: ServiceTypesFragmentViewModel by viewModels()
    private val buttonsAdapter by lazy { ButtonsAdapter() }

    private lateinit var exampleJobService: ComponentName
    private lateinit var serviceIntent: Intent;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exampleJobService = ComponentName(requireContext(), ExampleJobService::class.java)
        serviceIntent = Intent(requireContext(), ForegroundServiceExample::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        buttonsAdapter.items = listOf(
            ElevatedButton(
                name = "start Job Service Example",
                description = "background service",
                onClick = {
                    val info = JobInfo.Builder(123, exampleJobService)
                        .build()

                    requireContext().getSystemService(JobScheduler::class.java)
                        .schedule(info)
                }
            ),

            ElevatedButton(
                name = "stop Job Service Example",
                description = "background service",
                onClick = {
                    requireContext().getSystemService(JobScheduler::class.java)
                        .cancel(123)
                }
            ),

            ElevatedButton(
                name = "start foreground Service Example",
                description = "foreground service",
                onClick = {
                    requireContext().startForegroundService(serviceIntent)
                }
            ),

            ElevatedButton(
                name = "stop foreground Service Example",
                description = "foreground service",
                onClick = {
                    requireContext().stopService(serviceIntent)
                }
            ),


            )
    }


    private fun setupAdapter() {
        binding.rvIntentButtons.apply {
            adapter = buttonsAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

}

