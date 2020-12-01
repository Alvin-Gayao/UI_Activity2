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
import com.example.uielementsact2.models.Song
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

   // var songsArray = mutableListOf<String>("Fix You", "Superman", "Something More", "Perfect", "Far Away", "Parting Time", "Hello World", "Dream", "Cool Down", "Home", "Dynamite", "Sugar")
    var selectedSong = mutableListOf<String>()

class MainActivity : AppCompatActivity() {
    lateinit var songsListView : ListView
    lateinit var songsTableHandler: SongsTableHandler
    lateinit var songs : MutableList<Song>
    lateinit var adapter : ArrayAdapter<Song>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsListView = findViewById<ListView>(R.id.songsListView)
        songsTableHandler = SongsTableHandler(this)
        songs = songsTableHandler.read()
        adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, songs)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)
        }

    // when the song is long pressed
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.songs_menu, menu)
    }
    //when the menu option is clicked
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.add_to_queue -> {
                val song_id = songs[info.position].id
                val intent = Intent(applicationContext, SongsQueueActivity::class.java)
                intent.putExtra("song_id", song_id)
                startActivity(intent)
                true
            }
            R.id.edit_song -> {
                //get the selected book
                val song_id = songs[info.position].id
                //put it to extra
                val intent = Intent(applicationContext, EditSongActivity::class.java)
                intent.putExtra("song_id", song_id)
                //start intent
                startActivity(intent)
                true
            }
            R.id.delete_song -> {
                val book = songs[info.position]
                if(songsTableHandler.delete(book))
                {
                    songs.removeAt(info.position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Song was deleted.", Toast.LENGTH_SHORT)
                }else{
                    Toast.makeText(this, "Oops something went wrong.", Toast.LENGTH_SHORT)
                }
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
            R.id.add_song -> {
                startActivity(Intent(this, AddSongActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}



