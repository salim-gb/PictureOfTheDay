package com.example.pictureoftheday.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.ItemNoteBigBinding
import com.example.pictureoftheday.model.ListItem
import com.example.pictureoftheday.model.NoteBig

class NoteBigViewHolder private constructor(
    val binding: ItemNoteBigBinding,
    onClick: (ListItem, Boolean?) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    private var currentNote: NoteBig? = null

    init {
        itemView.setOnClickListener {
            currentNote?.let { note ->
                onClick(note, null)
            }
        }

        binding.checkboxFavorite.setOnClickListener {
            currentNote?.let { note ->
                onClick(note, true)
            }
        }
    }

    fun bind(note: NoteBig) {
        currentNote = note
        with(binding) {
            noteId.text = note.id.toString()
            if (note.image != null) {
                image.setImageResource(note.image)
            } else {
                image.setImageResource(R.drawable.space)
            }
            noteTitle.text = note.title
            noteDescriptionOne.text = note.descriptionOne
            noteDescriptionTwo.text = note.descriptionTwo
            checkboxFavorite.isChecked = note.isFavorite
        }
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ListItem, Boolean?) -> Unit): NoteBigViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteBigBinding.inflate(layoutInflater, parent, false)
            return NoteBigViewHolder(binding, onClick)
        }
    }
}