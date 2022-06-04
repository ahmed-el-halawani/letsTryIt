package com.newcore.letstryit.ui.intent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.core.util.adapters.ButtonsAdapter
import com.newcore.letstryit.core.util.adapters.ElevatedButton
import com.newcore.letstryit.databinding.FragmentButtonsAdapterBinding

class IntentInfosFragment :
    BaseFragment<FragmentButtonsAdapterBinding>(FragmentButtonsAdapterBinding::inflate) {

    private val vm: IntentInfosFragmentViewModel by viewModels()

    private val googleUrl = "https://www.google.com/"
    private val contacts = "content://contacts/people/"
    private val contactOne = "content://contacts/people/100"
    private val tellNumber = "tel:01097033133"

    private val buttonsAdapter by lazy { ButtonsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        buttonsAdapter.items = listOf(
            ElevatedButton(
                name = "Action View",
                description = "show google website",
                onClick = {
                    Intent(ACTION_VIEW).apply {
                        data = Uri.parse(googleUrl)
                        context?.startActivity(this)
                    }
                }
            ),
            ElevatedButton(
                name = "Action View",
                description = "show contents",
                onClick = {
                    Intent(ACTION_VIEW).apply {
                        data = Uri.parse(contacts)
                        context?.startActivity(this)
                    }
                }
            ),
            ElevatedButton(
                name = "ACTION DIAL",
                description = "tell number",
                onClick = {
                    try {
                        Intent(ACTION_DIAL).apply {
                            data = Uri.parse(tellNumber)
                            context?.startActivity(this)
                        }
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "activity not found", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            ElevatedButton(
                name = "ACTION VIEW",
                description = "tell number",
                onClick = {
                    try {
                        Intent(ACTION_VIEW).apply {
                            data = Uri.parse(tellNumber)
                            context?.startActivity(this)
                        }
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "activity not found", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            ElevatedButton(
                name = "ACTION VIEW",
                description = "show contact One",
                onClick = {
                    try {
                        Intent(ACTION_VIEW).apply {
                            data = Uri.parse(contactOne)
                            context?.startActivity(this)
                        }
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "activity not found", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            ElevatedButton(
                name = "ACTION VIEW",
                description = "open on splited window",
                onClick = {
                    try {
                        Intent(ACTION_VIEW).apply {
                            data = Uri.parse(contactOne)
                            context?.startActivity(this)
                        }
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "activity not found", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            ElevatedButton(
                name = "ACTION MAIN",
                description = "open main",
                onClick = {
                    try {
                        Intent(ACTION_MAIN).apply {
                            //                            data = Uri.parse(contactOne)
                            addCategory(CATEGORY_HOME)
                            context?.startActivity(this)
                        }
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "activity not found", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            ElevatedButton(
                name = "go To Intent Tag Test",
                description = "Explicit",
                onClick = {
                    try {
                        Intent(requireContext(), IntentTestActivity::class.java).apply {
                            requireContext().startActivity(this)
                        }
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "activity not found", Toast.LENGTH_SHORT).show()
                    }
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

