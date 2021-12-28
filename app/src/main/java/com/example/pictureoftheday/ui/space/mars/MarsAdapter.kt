package com.example.pictureoftheday.ui.space.mars

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.ItemViewMarsBinding
import com.example.pictureoftheday.model.MarsPhoto

class MarsAdapter :
    ListAdapter<MarsPhoto, MarsAdapter.MarsViewHolder>(MarsDiffCallback) {

    inner class MarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemViewMarsBinding.bind(itemView)

        fun bind(marsItem: MarsPhoto, context: Context) {
            with(binding) {
                binding.sol.text = context.getString(R.string.sol, marsItem.sol)
                cameraName.text = context.getString(R.string.camera_name, marsItem.camera?.name)
                roverName.text = context.getString(R.string.rover_name, marsItem.rover?.name)
                landingDate.text = context.getString(R.string.landing_date, marsItem.rover?.landingDate)
                launchDate.text = context.getString(R.string.launch_date, marsItem.rover?.launchDate)
                status.text = context.getString(R.string.rover_status, marsItem.rover?.status)
                binding.marsPictureImageView.load(marsItem.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_mars, parent, false)
        return MarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView.context)
    }
}

object MarsDiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
    override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
        return oldItem.id == newItem.id
    }
}