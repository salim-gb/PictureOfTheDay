package com.example.pictureoftheday.ui.space.moon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.ItemPictureMoonBinding
import com.example.pictureoftheday.model.MoonPicture
import com.example.pictureoftheday.model.ListItem

class MoonViewHolder private constructor(
    val binding: ItemPictureMoonBinding,
    onClick: (ListItem, Boolean?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentPicture: MoonPicture? = null

    init {
        itemView.setOnClickListener {
            currentPicture?.let {
                onClick(it, null)
            }
        }
    }

    fun bind(moonPicture: MoonPicture) {
        currentPicture = moonPicture
        binding.moonPicture.load(moonPicture.moonPicture)
        binding.title.text = binding.root.context.getString(R.string.moon_title, moonPicture.title)
        binding.bBox.text = binding.root.context.getString(R.string.moon_bbox, moonPicture.bBox)
        binding.abstractDes.text =
            binding.root.context.getString(R.string.moon_abstract, moonPicture.abstractDes)
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ListItem, Boolean?) -> Unit): MoonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPictureMoonBinding.inflate(layoutInflater, parent, false)
            return MoonViewHolder(binding, onClick)
        }
    }
}