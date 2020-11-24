package com.example.uielementsact2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

    var songsArray = mutableListOf<String>("Fix You", "Superman", "Something More", "Perfect", "Far Away", "Parting Time", "Hello World", "Dream", "Cool Down", "Home", "Dynamite", "Sugar")
    var selectedSong = mutableListOf<String>()

class MainActivity : AppCompatActivity() {
    private var songListView: ListView? = null
    private var adapter: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songListView = findViewById<ListView>(R.id.songsListView)
        adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, songsArray)
        songListView?.adapter = adapter
        registerForContextMenu(songListView)
        }

    // when the song is long pressed
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.songs_menu, menu)
    }
    //when the menu option is clicked
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_to_queue -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                selectedSong.add(songsArray[info.position])
                val snackbar = Snackbar.make(mainLayout,"View Listed Songs?",Snackbar.LENGTH_LONG)
                snackbar.setAction("Go",View.OnClickListener {
                    startActivity(Intent(applicationContext,SongsQueueActivity::class.java))
                })
                snackbar.show()
                true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.queue_page -> {
                startActivity(Intent(this, SongsQueueActivity::class.java))
                true
            }
            R.id.songs_page -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.albums_page -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}



