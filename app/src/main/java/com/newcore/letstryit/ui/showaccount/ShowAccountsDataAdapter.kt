package com.newcore.letstryit.ui.showaccount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newcore.letstryit.databinding.ComponentDataTileBinding
import com.newcore.letstryit.data.entites.Account

class ShowAccountsDataAdapter(private val items: List<Account>) :
    RecyclerView.Adapter<ShowAccountsDataAdapter.DataAdapterViewHolder>() {

    private var onDelete: ((Account, Int) -> Unit)? = null

    data class DataAdapterViewHolder(val binding: ComponentDataTileBinding) : RecyclerView
    .ViewHolder(binding.root)

    fun setOnDelete(onDelete: (Account, Int) -> Unit) {
        this.onDelete = onDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {
        return DataAdapterViewHolder(
            ComponentDataTileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvName.text = item.userName
            tvEmail.text = item.email
            tvPhoneNumber.text = item.password
            cvHolder.setOnClickListener {
                onDelete?.let { it1 -> it1(item, position) }
            }
        }

    }

    override fun getItemCount() =
        items.size
}