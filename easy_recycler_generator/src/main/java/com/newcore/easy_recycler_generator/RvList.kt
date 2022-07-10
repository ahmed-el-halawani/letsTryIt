package com.newcore.easy_recycler_generator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newcore.easy_recycler_generator.databinding.ItemContainerBinding

class RvList : RecyclerView.Adapter<RvList.RvListViewHolder>() {
    class RvListViewHolder(private val viewHolder: ItemContainerBinding) :
        RecyclerView.ViewHolder(viewHolder.root) {
        operator fun invoke(view: View) {
            if (view.parent != null) {
                (view.parent as ViewGroup).removeView(view)
            }
            viewHolder.frameLayout.removeAllViews()
            viewHolder.frameLayout.addView(view)
        }
    }

    private val children: MutableList<ViewGeneratorHolder<*, *>> = mutableListOf()

    fun addAll(listOfAddedViews: List<ViewGeneratorHolder<*, *>>) {
        val positionStart = children.size - 1
        children.addAll(listOfAddedViews)
        notifyItemRangeChanged(positionStart, listOfAddedViews.size)
    }

    fun add(view: ViewGeneratorHolder<*, *>) {
        children.add(view)
        notifyItemInserted(children.size - 1)
    }

    fun remove(view: ViewGeneratorHolder<*, *>) {
        val itemPosition = children.indexOf(view)
        children.remove(view)
        notifyItemRemoved(itemPosition)
    }

    fun remove(position: Int) {
        children.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvListViewHolder {
        return RvListViewHolder(ItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ))
    }

    override fun onBindViewHolder(holder: RvListViewHolder, position: Int) {
        holder(children[position].view)
    }

    override fun getItemCount() = children.size
}