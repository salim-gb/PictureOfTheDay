package com.example.pictureoftheday.ui.space.mars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.ItemViewMarsBinding
import com.example.pictureoftheday.model.MarsPhoto
import com.example.pictureoftheday.model.ListItem
import timber.log.Timber

class MarsViewHolder private constructor(
    val binding: ItemViewMarsBinding,
    onClick: (ListItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentMarsPicture: MarsPhoto? = null

    init {
        itemView.setOnClickListener {
            currentMarsPicture?.let {
                onClick(it)
            }
        }
    }

    fun bind(marsPhoto: MarsPhoto) {

        currentMarsPicture = marsPhoto

        with(binding) {
            Timber.i("sol: $sol")
            sol.text = itemView.context.getString(R.string.sol, marsPhoto.sol)
            cameraName.text =
                itemView.context.getString(R.string.camera_name, marsPhoto.camera?.name)
            roverName.text = itemView.context.getString(R.string.rover_name, marsPhoto.rover?.name)
            landingDate.text =
                itemView.context.getString(R.string.landing_date, marsPhoto.rover?.landingDate)
            launchDate.text =
                itemView.context.getString(R.string.launch_date, marsPhoto.rover?.launchDate)
            status.text = itemView.context.getString(R.string.rover_status, marsPhoto.rover?.status)
            marsPictureImageView.load(marsPhoto.url)
        }
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (ListItem) -> Unit): MarsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemViewMarsBinding.inflate(layoutInflater, parent, false)
            return MarsViewHolder(binding, onClick)
        }
    }
}