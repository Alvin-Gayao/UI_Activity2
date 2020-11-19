package com.example.uielementsact2

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_albums.*

class AlbumsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val gridview = findViewById<View>(R.id.albumGridView) as GridView
        gridview.adapter = AlbumAdapter(this)
        gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent,v,position,id ->
                // Sending image id to AlbumDetailsActivity
                val intent = Intent(applicationContext, AlbumDetailsActivity::class.java)
                // passing array index
                intent.putExtra("id",position)
                startActivity(intent)
            }
    }

}