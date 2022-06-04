package com.newcore.letstryit.ui.showaccount

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.databinding.FragmentShowContentProviderDataBinding
import com.newcore.letstryit.data.repositories.AccountRepo

class ShowAccountsDataFragment : BaseFragment<FragmentShowContentProviderDataBinding>
    (FragmentShowContentProviderDataBinding::inflate) {

    val accountRepo by lazy {
        AccountRepo(requireContext().contentResolver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var items = accountRepo.getAccounts().toMutableList()

        val ad = ShowAccountsDataAdapter(items).apply {
            setOnDelete { user, position ->
                items.remove(user)
                notifyItemRemoved(position)
                if (accountRepo.delete(user.id ?: 0) < 0) {
                    items = accountRepo.getAccounts().toMutableList()
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