package com.example.uielementsact2

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetailsActivity : AppCompatActivity() {
    val album1 = arrayOf("Fix You", "Superman", "Something More", "Perfect")
    val album2  = arrayOf("Far Away", "Parting Time", "Hello World", "Dream")
    val album3 = arrayOf("Cool Down", "Home", "Dynamite", "Sugar")
    val songsCollection = arrayOf(album1, album2, album3)
    private val albumNames = arrayOf("Album 1", "Album 2", "Album 3")
    private var songListView: ListView? = null
    private var adapter: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        // Get intent data
        val intent = intent
        // Get Selected Image Id
        val position = intent.extras!!.getInt("id")
        val imageAdapter = AlbumAdapter(this)
        val imageView: ImageView = findViewById<View>(R.id.imageView) as ImageView
        imageView.setImageResource(imageAdapter.albumImages[position])
        textView.text = albumNames[position]

        songListView = findViewById<ListView>(R.id.albumListView)
        adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_expandable_list_item_1, songsCollection[position])
        songListView?.adapter = adapter

    }
}

