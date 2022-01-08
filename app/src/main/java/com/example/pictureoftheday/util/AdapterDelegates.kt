package com.example.pictureoftheday.util

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.ListItem

class AdapterDelegates(
    private val delegates: List<Delegate>,
    var currentList: List<ListItem> = emptyList(),
    private val onClick: (ListItem) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        delegates.indexOfFirst { delegate -> delegate.forItem(currentList[position]) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].getViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].bindViewHolder(holder, currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}