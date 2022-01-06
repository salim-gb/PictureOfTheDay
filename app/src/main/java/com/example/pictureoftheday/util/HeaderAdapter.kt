package com.example.pictureoftheday.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.databinding.HeaderItemBinding

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var notesCount: Int = 0

    class HeaderViewHolder private constructor(
        val binding: HeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(notesCount: Int) {
            binding.notesNumberCount.text = notesCount.toString()
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderItemBinding.inflate(layoutInflater, parent, false)
                return HeaderViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(notesCount)
    }

    override fun getItemCount(): Int = 1

    fun updateNotesCount(updatedNotesCount: Int) {
        notesCount = updatedNotesCount
        notifyDataSetChanged()
    }
}