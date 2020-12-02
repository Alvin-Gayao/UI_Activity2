package com.example.uielementsact2

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import com.example.uielementsact2.models.Album

class AlbumsActivity : AppCompatActivity() {
    lateinit var albums : MutableList<Album>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val gridview = findViewById<View>(R.id.albumGridView) as GridView
        gridview.adapter = AlbumAdapter(this)
        gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent,v,position,id ->
                // Sending to AlbumDetailsActivity
                val intent = Intent(applicationContext, AlbumDetailsActivity::class.java)
                // passing array index
                intent.putExtra("id",position)
                startActivity(intent)
            }
        registerForContextMenu(gridview)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.albums_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_album -> {
                startActivity(Intent(this, AddAlbumActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu,v,menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.albums_option, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when(item.itemId){
            R.id.edit_album -> {
                //get selected album
                val album_id = albums[info.position].id
                //put it to extra
                val intent = Intent(applicationContext, EditAlbumActivity::class.java)
                intent.putExtra("album_id", album_id)
                startActivity(intent)
                true
            }
        }
        return super.onContextItemSelected(item)
    }
}