package com.example.pictureoftheday.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.databinding.ItemNoteSmallBinding
import com.example.pictureoftheday.model.NoteSmall
import com.example.pictureoftheday.util.ListItem

class NoteSmallViewHolder private constructor(
    val binding: ItemNoteSmallBinding,
    onClick: (ListItem) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    private var currentNote: NoteSmall? = null

    init {
        itemView.setOnClickListener {
            currentNote?.let { note ->
                onClick(note)
            }
        }
    }

    fun bind(note: NoteSmall) {
        currentNote = note
        binding.noteId.text = note.id.toString()
        binding.noteTitle.text = note.title
        binding.noteDescription.text = note.description
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ListItem) -> Unit): NoteSmallViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteSmallBinding.inflate(layoutInflater, parent, false)
            return NoteSmallViewHolder(binding, onClick)
        }
    }
}