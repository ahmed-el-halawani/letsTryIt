package com.newcore.letstryit.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newcore.letstryit.databinding.ItemIntentInfoButtonBinding

class ButtonsAdapter() :
    RecyclerView.Adapter<ButtonsAdapter.DataAdapterViewHolder>() {

    var items: List<IntentButton> = emptyList()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }

    data class DataAdapterViewHolder(val binding: ItemIntentInfoButtonBinding) : RecyclerView
    .ViewHolder(binding.root) {
        operator fun invoke(item: IntentButton) {
            binding.apply {
                tvName.text = item.name
                tvDescription.text = item.description
                button.setOnClickListener { item.onClick() }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {
        return DataAdapterViewHolder(
            ItemIntentInfoButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        holder(items[position])
    }

    override fun getItemCount() =
        items.size
}