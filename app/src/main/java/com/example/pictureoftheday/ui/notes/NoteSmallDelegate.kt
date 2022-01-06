package com.example.pictureoftheday.ui.notes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.model.NoteSmall
import com.example.pictureoftheday.util.Delegate
import com.example.pictureoftheday.util.ListItem

class NoteSmallDelegate : Delegate {

    override fun forItem(listItem: ListItem): Boolean = listItem is NoteSmall

    override fun getViewHolder(
        parent: ViewGroup,
        onClick: (ListItem) -> Unit
    ): RecyclerView.ViewHolder =
        NoteSmallViewHolder.from(parent, onClick)

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ListItem) {
        (viewHolder as NoteSmallViewHolder).let { noteViewHolder ->
            val note = item as NoteSmall
            noteViewHolder.bind(note)
        }
    }
}