package com.example.pictureoftheday.ui.notes

import android.os.Build
import android.text.SpannableString
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

            noteDescriptionOne.text = textWithSpannable(note.descriptionOne)
            noteDescriptionTwo.text = textWithSpannable(note.descriptionTwo)
            checkboxFavorite.isChecked = note.isFavorite
        }
    }

    private fun textWithSpannable(text: String): SpannableString {
        val spannableString = SpannableString(text)
        val startIndex = 0
        val endIndex = text.length
        val color = ContextCompat.getColor(itemView.context, R.color.blue)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            spannableString.setSpan(BulletSpan(100, color), startIndex, endIndex, 0)
        } else {
            spannableString.setSpan(
                BulletSpan(10, color, 10),
                startIndex,
                endIndex,
                0
            )
        }

        return spannableString
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ListItem, Boolean?) -> Unit): NoteBigViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteBigBinding.inflate(layoutInflater, parent, false)
            return NoteBigViewHolder(binding, onClick)
        }
    }
}