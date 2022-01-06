package com.example.pictureoftheday.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.databinding.ItemNoteBigBinding
import com.example.pictureoftheday.model.NoteBig
import com.example.pictureoftheday.util.ListItem

class NoteBigViewHolder private constructor(
    val binding: ItemNoteBigBinding,
    onClick: (ListItem) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    private var currentNote: NoteBig? = null

    init {
        itemView.setOnClickListener {
            currentNote?.let { note ->
                onClick(note)
            }
        }
    }

    fun bind(note: NoteBig) {
        currentNote = note
        binding.noteId.text = note.id.toString()
        binding.noteTitle.text = note.title
        binding.noteDescriptionOne.text = note.descriptionOne
        binding.noteDescriptionTwo.text = note.descriptionTwo
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ListItem) -> Unit): NoteBigViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteBigBinding.inflate(layoutInflater, parent, false)
            return NoteBigViewHolder(binding, onClick)
        }
    }
}