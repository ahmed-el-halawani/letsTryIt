package com.newcore.letstryit.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.core.adapters.ButtonsAdapter
import com.newcore.letstryit.core.adapters.IntentButton
import com.newcore.letstryit.databinding.FragmentIntentInfosBinding

class HomeFragment : BaseFragment<FragmentIntentInfosBinding>(FragmentIntentInfosBinding::inflate) {

    private val buttonsAdapter by lazy { ButtonsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

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
        )


    }

    private fun setupAdapter() {
        binding.rvIntentButtons.apply {
            adapter = buttonsAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }
}