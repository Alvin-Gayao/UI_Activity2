package com.example.uielementsact2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class SongsQueueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_queue)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedSong)
        val listSongView = findViewById<ListView>(R.id.songListView)
        listSongView.adapter = adapter
    }
}