package com.pavesid.niksl.core

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class OverlapDecoration(private var vertOverlap: Int = -150) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0, if (parent.getChildAdapterPosition(view) == 0) 0 else vertOverlap, 0, 0)
    }
}
