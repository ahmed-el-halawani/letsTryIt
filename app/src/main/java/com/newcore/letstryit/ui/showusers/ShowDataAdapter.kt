package com.newcore.letstryit.ui.showusers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newcore.letstryit.databinding.ComponentDataTileBinding
import com.newcore.letstryit.model.entites.User

class ShowDataAdapter(private val items: List<User>) :
    RecyclerView.Adapter<ShowDataAdapter.DataAdapterViewHolder>() {

    private var onDelete: ((User, Int) -> Unit)? = null

    data class DataAdapterViewHolder(val binding: ComponentDataTileBinding) : RecyclerView
    .ViewHolder(binding.root)

    fun setOnDelete(onDelete: (User, Int) -> Unit) {
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
            tvName.text = item.name
            tvEmail.text = item.email
            tvPhoneNumber.text = item.phoneNumber
            cvHolder.setOnClickListener {
                onDelete?.let { it1 -> it1(item, position) }
            }
        }

    }

    override fun getItemCount() =
        items.size
}