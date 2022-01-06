package com.example.pictureoftheday.util

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class AdapterDelegates(
    private val delegates: List<Delegate>,
    private val onClick: (ListItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var currentList: List<ListItem> = emptyList()
        set(value) {
            val callback = CommonCallbackImpl(
                oldItems = field,
                newItems = value,
                /* for another comparison
                areItemsTheSameImpl = { },
                areContentsTheSameImpl = { },
                getChangePayloadImpl = { }
                 */
            )
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemViewType(position: Int): Int =
        delegates.indexOfFirst { delegate -> delegate.forItem(currentList[position]) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].getViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].bindViewHolder(holder, currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}