package com.example.pictureoftheday.ui.notes

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.databinding.ItemNoteSmallBinding
import com.example.pictureoftheday.model.ListItem
import com.example.pictureoftheday.model.NoteSmall

class NoteSmallViewHolder private constructor(
    val binding: ItemNoteSmallBinding,
    onClick: (ListItem, Boolean?) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {

    private var currentNote: NoteSmall? = null

    init {
        itemView.setOnClickListener {
            currentNote?.let { note ->
                onClick(note, null)
            }
        }
        itemView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    binding.noteDragBtn.visibility = View.GONE
                    v.performClick()
                }
                MotionEvent.ACTION_DOWN -> {
                    binding.noteDragBtn.visibility = View.VISIBLE
                }
                MotionEvent.ACTION_CANCEL -> {
                    binding.noteDragBtn.visibility = View.GONE
                }
            }
            true
        }

        binding.checkboxFavorite.setOnClickListener {
            currentNote?.let { note ->
                onClick(note, true)
            }
        }
    }

    fun bind(note: NoteSmall) {
        currentNote = note
        binding.noteId.text = note.id.toString()
        binding.noteTitle.text = note.title
        binding.noteDescription.text = note.description
        binding.checkboxFavorite.isChecked = note.isFavorite
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ListItem, Boolean?) -> Unit): NoteSmallViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteSmallBinding.inflate(layoutInflater, parent, false)
            return NoteSmallViewHolder(binding, onClick)
        }
    }
}