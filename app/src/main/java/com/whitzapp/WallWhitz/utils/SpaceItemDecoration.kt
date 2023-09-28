package com.whitzapp.WallWhitz.utils

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import javax.inject.Inject


class SpaceItemDecoration @Inject constructor() : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams

        val viewSpanIndex = layoutParams.spanIndex

        Log.d("dknnkdmcd",viewSpanIndex.toString())

        if(viewSpanIndex == 0 ){
            outRect.right = 20
        }else{
            outRect.left = 20
        }

        outRect.top = 20


    }
}