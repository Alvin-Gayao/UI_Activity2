package com.example.uielementsact2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementsact2.models.Album
import com.example.uielementsact2.models.Song
import java.sql.Date

class AddAlbumActivity : AppCompatActivity() {
    lateinit var addAlbumButton : Button
    lateinit var albumTitleET : EditText
    lateinit var albumReleaseET : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album)

        val databaseHandler = AlbumsTableHandler(this)
        albumTitleET = findViewById(R.id.albumTitleET)
        albumReleaseET = findViewById(R.id.albumReleaseET)

        addAlbumButton = findViewById(R.id.addAlbumButton)
        addAlbumButton.setOnClickListener {
            //get the fields from form
            val title = albumTitleET.text.toString()
            val release_date = albumReleaseET.text as Date
            //assign to a book model
            val album = Album(title = title,  release_date = release_date)
            //save it to database
            if(databaseHandler.create(album)) {
                Toast.makeText(this, "Album was added", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Oops something went wrong.", Toast.LENGTH_SHORT).show()
            }
            clearFields()
        }
    }
    fun clearFields(){
        albumTitleET.text.clear()
        albumReleaseET.text.clear()
    }
}