package com.example.uielementsact2

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.uielementsact2.models.Album


class AlbumAdapter(c: Context) : BaseAdapter() {
    // Add all our images to arraylist
    lateinit var context : Context
    var albumsTableHandler = AlbumsTableHandler(c)
    var albums = albumsTableHandler.read()


    private val mContext: Context
    override fun getCount(): Int {
        return albums.size
    }

    override fun getItem(position: Int): Any? {
        return albums[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val textView = TextView(mContext)
        textView.setLayoutParams(AbsListView.LayoutParams(500, 500))
        textView.setPadding(8, 8, 8, 8)
        textView.setText(albums[position].title)
        return textView

    }

    init {
        mContext = c
        this.albums = albums
    }
}
