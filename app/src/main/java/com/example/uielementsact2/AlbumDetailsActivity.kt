package com.example.uielementsact2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetailsActivity : AppCompatActivity() {
    var album1 = mutableListOf("Fix You", "Superman", "Something More", "Perfect")
    var album2 = mutableListOf("Far Away", "Parting Time", "Hello World", "Dream")
    var album3 = mutableListOf("Cool Down", "Home", "Dynamite", "Sugar")
    //private val albumNames = arrayOf("Album 1", "Album 2", "Album 3")
    private var songListView: ListView? = null
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        // Get intent data
        val intent = intent
        // Get Selected Image Id
        val position = intent.extras!!.getInt("id")
        val albumAdapter = AlbumAdapter(this)
        val textView: TextView = findViewById<TextView>(R.id.textView) as TextView
        textView.setText(albumAdapter.albums[position].title)

        songListView = findViewById<ListView>(R.id.albumListView)
        if(position == 0) {
            adapter = ArrayAdapter<String>(applicationContext,
                android.R.layout.simple_expandable_list_item_1,
                album1)
        }
        else if(position == 1){
            adapter = ArrayAdapter<String>(applicationContext,
                android.R.layout.simple_expandable_list_item_1,
                album2)
        }else{
            adapter = ArrayAdapter<String>(applicationContext,
                android.R.layout.simple_expandable_list_item_1,
                album3)
        }
        songListView?.adapter = adapter
        registerForContextMenu(songListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?,v: View?,menuInfo: ContextMenu.ContextMenuInfo?)
    {
        super.onCreateContextMenu(menu,v,menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.album_list_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.song_remove -> {
                val intent = intent
                val position = intent.extras!!.getInt("id")
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Remove song from album?")
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener{
                            dialog, which ->
                        if(position == 0){
                            album1.removeAt(info.position)
                        }
                        else if(position == 1){
                            album2.removeAt(info.position)
                        }
                        else{
                            album3.removeAt(info.position)
                        }
                        adapter?.notifyDataSetChanged()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener{
                        dialog, which ->
                    })
                    val alert = dialogBuilder.create()
                    alert.setTitle("Album Dialog")
                    alert.show()
            }
        }
        return super.onContextItemSelected(item)
    }

}

