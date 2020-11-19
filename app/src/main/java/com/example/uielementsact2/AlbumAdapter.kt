package com.example.uielementsact2

import android.R.drawable
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class AlbumAdapter(c: Context) : BaseAdapter() {
    // Add all our images to arraylist
    var albumImages = arrayOf<Int>(
        R.drawable.boyce,
        R.drawable.opm,
        R.drawable.sserenade
    )

    private val mContext: Context
    override fun getCount(): Int {
        return albumImages.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(mContext)
        imageView.setLayoutParams(AbsListView.LayoutParams(500, 500))
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        imageView.setPadding(8, 8, 8, 8)
        imageView.setImageResource(albumImages[position])
        return imageView
    }

    init {
        mContext = c
    }
}