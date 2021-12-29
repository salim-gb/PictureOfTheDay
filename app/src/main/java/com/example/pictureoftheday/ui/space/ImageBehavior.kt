package com.example.pictureoftheday.ui.space

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class ImageBehavior(context: Context, attr: AttributeSet) :
    CoordinatorLayout.Behavior<ImageView>(context, attr) {
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: ImageView,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: ImageView,
        dependency: View
    ): Boolean {
        val bar = dependency as AppBarLayout

        val height = bar.height / 2

        if (abs(bar.y) > (height / 1.5)) {
            child.visibility = View.GONE
        } else {
            child.visibility = View.VISIBLE

            child.alpha = (height - abs(bar.y)) / (height)
            child.scaleY = (height - abs(bar.y)) / (height)
            child.scaleX = (height - abs(bar.y)) / (height)
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}