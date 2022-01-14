package com.example.pictureoftheday.ui.notes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.NoteBig
import com.example.pictureoftheday.util.Delegate
import com.example.pictureoftheday.model.ListItem

class NoteBigDelegate : Delegate {

    override fun forItem(listItem: ListItem): Boolean = listItem is NoteBig

    override fun getViewHolder(
        parent: ViewGroup,
        onClick: (ListItem, Boolean?) -> Unit
    ): RecyclerView.ViewHolder =
        NoteBigViewHolder.from(parent, onClick)

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ListItem) {
        (viewHolder as NoteBigViewHolder).let { noteViewHolder ->
            val note = item as NoteBig
            noteViewHolder.bind(note)
        }
    }
}