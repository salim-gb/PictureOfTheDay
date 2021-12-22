package com.example.pictureoftheday.util

import android.view.View
import android.widget.ProgressBar
import coil.imageLoader
import coil.request.Disposable
import coil.request.ImageRequest
import com.example.pictureoftheday.R
import com.example.pictureoftheday.widget.CustomImageView

object CoilHelper {

    private var disposable: Disposable? = null

    fun loadWithCoil(
        imageView: CustomImageView,
        url: String?,
        progressBar: ProgressBar,
        view: View
    ) {

        disposable?.dispose()

        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .placeholder(R.drawable.ic_baseline_image_24)
            .crossfade(true)
            .crossfade(1000)
            .target(
                onStart = {
                    progressBar.visibility = View.VISIBLE
                    view.visibility = View.VISIBLE
                },
                onSuccess = {
                    imageView.setImageDrawable(it)
                    progressBar.visibility = View.GONE
                    view.visibility = View.GONE
                }
            ).build()

        disposable = imageView.context.imageLoader.enqueue(request)
    }
}