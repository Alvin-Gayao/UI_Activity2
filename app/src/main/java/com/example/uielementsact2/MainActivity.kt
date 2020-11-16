package com.example.uielementsact2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songsArray = arrayOf("Fix You", "Superman", "Something More", "Perfect", "Far Away", "Parting Time", "Hello World", "Dream", "Cool Down", "Home")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, songsArray)
        val songsListView = findViewById<ListView>(R.id.songsListView)
        songsListView.adapter = adapter

        songsListView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val songsArray = findViewById<ListView>(R.id.songsListView)
            registerForContextMenu(songsArray[position])
        }
    }
    // when the image is long pressed
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.song_details_menu, menu)
    }
    //when the menu option is clicked
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_queue -> {
                Toast.makeText(this, "Added to queue!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
}