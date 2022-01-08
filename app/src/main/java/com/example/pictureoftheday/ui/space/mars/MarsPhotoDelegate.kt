package com.example.pictureoftheday.ui.space.mars

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.MarsPhoto
import com.example.pictureoftheday.util.Delegate
import com.example.pictureoftheday.model.ListItem

class MarsPhotoDelegate : Delegate {

    override fun forItem(listItem: ListItem): Boolean = listItem is MarsPhoto

    override fun getViewHolder(
        parent: ViewGroup,
        onClick: (ListItem) -> Unit
    ): RecyclerView.ViewHolder =
        MarsViewHolder.from(parent, onClick)

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ListItem) {
        (viewHolder as MarsViewHolder).let { marsViewHolder ->
            val marsPhoto = item as MarsPhoto
            marsViewHolder.bind(marsPhoto)
        }
    }
}