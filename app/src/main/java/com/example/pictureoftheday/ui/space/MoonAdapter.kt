package com.example.pictureoftheday.ui.space

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.ItemPictureMoonBinding
import com.example.pictureoftheday.model.MoonPicture

class MoonAdapter(
    private val moonPicturesList: List<MoonPicture>,
    private val onClick: (MoonPicture) -> Unit
) :
    RecyclerView.Adapter<MoonAdapter.ViewHolder>() {

    class ViewHolder(view: View, onClick: (MoonPicture) -> Unit) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPictureMoonBinding.bind(view)
        private var currentPicture: MoonPicture? = null

        init {
            view.setOnClickListener {
                currentPicture?.let {
                    onClick(it)
                }
            }
        }

        fun bind(item: MoonPicture, context: Context) {
            currentPicture = item
            binding.moonPicture.load(item.moonPicture)
            binding.title.text = context.getString(R.string.moon_title, item.title)
            binding.bBox.text = context.getString(R.string.moon_bbox, item.bBox)
            binding.abstractDes.text = context.getString(R.string.moon_abstract, item.abstractDes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_picture_moon, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return moonPicturesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moonPicturesList[position], holder.itemView.context)
    }
}