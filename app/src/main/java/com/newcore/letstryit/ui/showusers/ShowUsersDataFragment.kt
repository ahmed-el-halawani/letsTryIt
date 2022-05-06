package com.newcore.letstryit.ui.showusers

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentShowContentProviderDataBinding
import com.newcore.letstryit.model.repositories.UserRepo

class ShowUsersDataFragment : BaseFragment<FragmentShowContentProviderDataBinding>
    (FragmentShowContentProviderDataBinding::inflate) {

    val userRepo by lazy {
        UserRepo(requireContext().contentResolver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var items = userRepo.getUsers().toMutableList()

        val ad = ShowDataAdapter(items).apply {
            setOnDelete { user, position ->
                items.remove(user)
                notifyItemRemoved(position)
                if (userRepo.delete(user.id ?: 0) < 0) {
                    items = userRepo.getUsers().toMutableList()
                    notifyItemChanged(position)
                }
            }
        }

        binding.lvSimple.apply {
            adapter = ad
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
        }

    }



}