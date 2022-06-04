package com.newcore.letstryit.core.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newcore.letstryit.databinding.ItemTableRowBinding

class TableAdapter : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    var items: List<TableRowData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    data class TableViewHolder(val binding: ItemTableRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        operator fun invoke(tableRowData: TableRowData) = binding.apply {
            tvColumn1.text = tableRowData.column1
            tvColumn2.text = tableRowData.column2
            tvColumn3.text = tableRowData.column3
            tvColumn4.text = tableRowData.column4
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder =
        TableViewHolder(
            ItemTableRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder(items[position])
    }

    override fun getItemCount() = items.size
}

