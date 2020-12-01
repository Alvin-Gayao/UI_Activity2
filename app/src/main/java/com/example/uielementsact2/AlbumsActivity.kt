package com.example.uielementsact2

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import com.example.uielementsact2.models.Album
import com.example.uielementsact2.models.Song
import kotlinx.android.synthetic.main.activity_albums.*

class AlbumsActivity : AppCompatActivity() {

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
}