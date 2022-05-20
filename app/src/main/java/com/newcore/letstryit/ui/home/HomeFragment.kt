package com.newcore.letstryit.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.core.adapters.ButtonsAdapter
import com.newcore.letstryit.core.adapters.IntentButton
import com.newcore.letstryit.databinding.FragmentIntentInfosBinding

class HomeFragment : BaseFragment<FragmentIntentInfosBinding>(FragmentIntentInfosBinding::inflate) {

    private val buttonsAdapter by lazy { ButtonsAdapter() }

    private val locationManager by lazy {
        requireContext().getSystemService(LocationManager::class.java)
    }

    private lateinit var permissionResult: ActivityResultLauncher<Array<String>>

    private fun getMyLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionResult.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
            return
        }
        val providers = locationManager.allProviders

        val currentLocation = providers.firstOrNull()?.let {
            locationManager.getLastKnownLocation(it)
        }

        Log.e("locationManagerTest", "providers: $providers")
        Log.e("locationManagerTest", "currentLocation: $currentLocation")

        showToast("providers: $providers \n currentLocation: $currentLocation")
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        permissionResult = registerForActivityResult(ActivityResultContracts
            .RequestMultiplePermissions()) {
            it.filter { filter -> !filter.value }.isEmpty().also { isTrue ->
                if (isTrue) {
                    getMyLocation()
                } else {
                    Toast.makeText(requireContext(), "permission denied", Toast
                        .LENGTH_SHORT).show()
                }
            }

        }


        buttonsAdapter.items = listOf(
            IntentButton(
                name = "Login",
                onClick = {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                    )
                }
            ),
            IntentButton(
                name = "Register",
                onClick = {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
                    )
                }
            ),
            IntentButton(
                name = "Content Provider",
                onClick = {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToContentProviderFragment()
                    )
                }
            ),
            IntentButton(
                name = "Intent Infos",
                onClick = {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToIntentInfosFragment()
                    )
                }
            ),

            IntentButton(
                name = "Location manager Test",
                onClick = {
                    getMyLocation()
                }
            ),
        )


    }

    private fun setupAdapter() {
        binding.rvIntentButtons.apply {
            adapter = buttonsAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }
}