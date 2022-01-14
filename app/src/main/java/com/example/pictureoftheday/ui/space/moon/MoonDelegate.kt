package com.example.pictureoftheday.ui.space.moon

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.MoonPicture
import com.example.pictureoftheday.util.Delegate
import com.example.pictureoftheday.model.ListItem

class MoonDelegate : Delegate {

    override fun forItem(listItem: ListItem): Boolean = listItem is MoonPicture

    override fun getViewHolder(
        parent: ViewGroup,
        onClick: (ListItem, Boolean?) -> Unit
    ): RecyclerView.ViewHolder = MoonViewHolder.from(parent, onClick)

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ListItem) {
        (viewHolder as MoonViewHolder).let { moonViewHolder ->
            val moonPicture = item as MoonPicture
            moonViewHolder.bind(moonPicture)
        }
    }
}