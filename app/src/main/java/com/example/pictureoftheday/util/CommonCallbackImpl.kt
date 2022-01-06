package com.example.pictureoftheday.util

import androidx.recyclerview.widget.DiffUtil

class CommonCallbackImpl<T>(
    private val oldItems: List<T>,
    private val newItems: List<T>,
    private val areItemsTheSameImpl: (oldItem: T, newItem: T) -> Boolean
    = { oldItem, newItem -> oldItem == newItem },
    private val areContentsTheSameImpl: (oldItem: T, newItem: T) -> Boolean
    = { oldItem, newItem -> oldItem == newItem },
    private val getChangePayloadImpl: (oldItem: T, newItem: T) -> Any?
    = { _, _ -> null }
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return areItemsTheSameImpl(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return areContentsTheSameImpl(oldItem, newItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return getChangePayloadImpl(oldItem, newItem)
    }
}