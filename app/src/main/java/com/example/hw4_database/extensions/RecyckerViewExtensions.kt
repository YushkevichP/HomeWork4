package com.example.hw4_database.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addSpaceDecoration(bottomSpace:Int){
    addItemDecoration(object : RecyclerView.ItemDecoration(){
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            val itemCount = parent.adapter?.itemCount?:return
            val position = parent.getChildAdapterPosition(view)
            if (position!= (itemCount-1)){
                outRect.bottom = bottomSpace
            }
        }
    })
}