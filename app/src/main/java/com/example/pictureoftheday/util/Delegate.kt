package com.example.pictureoftheday.util

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.ListItem

interface Delegate {
    fun forItem(listItem: ListItem): Boolean
    fun getViewHolder(parent: ViewGroup, onClick: (ListItem) -> Unit): RecyclerView.ViewHolder
    fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ListItem)
}