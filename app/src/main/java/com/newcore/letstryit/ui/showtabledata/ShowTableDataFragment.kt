package com.newcore.letstryit.ui.showtabledata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.newcore.letstryit.core.BaseFragment
import com.newcore.letstryit.core.adapters.TableAdapter
import com.newcore.letstryit.databinding.FragmentShowDataAdapterBinding

class ShowTableDataFragment :
    BaseFragment<FragmentShowDataAdapterBinding>(FragmentShowDataAdapterBinding::inflate) {

    private val tableDataFragmentArgs: ShowTableDataFragmentArgs by navArgs()

    private val tableAdapter: TableAdapter by lazy {
        TableAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHeader()
        initTableRecycler()
        tableAdapter.items = tableDataFragmentArgs.data.toList()
    }

    private fun initHeader() = binding.tableHeader.apply {
        val header = tableDataFragmentArgs.header
        tvColumn1.text = header.column1
        tvColumn2.text = header.column2
        tvColumn3.text = header.column3
        tvColumn4.text = header.column4
    }

    private fun initTableRecycler() = binding.rvTable.apply {
        adapter = tableAdapter
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


}

