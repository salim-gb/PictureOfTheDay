package com.example.pictureoftheday.ui.space.mars

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pictureoftheday.R
import com.example.pictureoftheday.model.MarsPhoto
import com.example.pictureoftheday.widget.CustomImageView
import com.google.android.material.textview.MaterialTextView
import timber.log.Timber

class MarsAdapter:
    ListAdapter<MarsPhoto, MarsAdapter.MarsViewHolder>(MarsDiffCallback) {

    inner class MarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val solTextView = itemView.findViewById<MaterialTextView>(R.id.sol)
        private val cameraNameTextView = itemView.findViewById<MaterialTextView>(R.id.cameraName)
        private val roverName= itemView.findViewById<MaterialTextView>(R.id.roverName)
        private val landingDateTextView = itemView.findViewById<MaterialTextView>(R.id.landingDate)
        private val launchDateTextView = itemView.findViewById<MaterialTextView>(R.id.launchDate)
        private val statusTextView = itemView.findViewById<MaterialTextView>(R.id.status)
        private val marsPictureImageView = itemView.findViewById<CustomImageView>(R.id.marsPictureImageView)

        fun bind(marsItem: MarsPhoto, context: Context) {
            solTextView.text = context.getString(R.string.sol, marsItem.sol)
            cameraNameTextView.text = context.getString(R.string.camera_name, marsItem.camera?.name)
            roverName.text = context.getString(R.string.rover_name, marsItem.rover?.name)
            landingDateTextView.text = context.getString(R.string.landing_date, marsItem.rover?.landingDate)
            launchDateTextView.text = context.getString(R.string.launch_date, marsItem.rover?.launchDate)
            statusTextView.text = context.getString(R.string.rover_status, marsItem.rover?.status)
            marsPictureImageView.load("https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/02071/opgs/edr/fcam/FLB_581357707EDR_F0701752FHAZ00337M_.JPG")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_mars, parent, false)
        Timber.i("view holder created")
        return MarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView.context)
    }
}

object MarsDiffCallback: DiffUtil.ItemCallback<MarsPhoto>() {
    override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
        return oldItem.id == newItem.id
    }
}