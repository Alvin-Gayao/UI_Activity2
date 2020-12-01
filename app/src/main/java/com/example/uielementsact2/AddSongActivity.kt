package com.example.uielementsact2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementsact2.models.Song

class AddSongActivity : AppCompatActivity() {
    lateinit var addSongButton : Button
    lateinit var songTitleET : EditText
    lateinit var songArtistET : EditText
    lateinit var songAlbumET : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        val databaseHandler = SongsTableHandler(this)
        songTitleET = findViewById(R.id.songTitleET)
        songArtistET = findViewById(R.id.songArtistET)
        songAlbumET = findViewById(R.id.songAlbumET)

        addSongButton = findViewById(R.id.addSongButton)
        addSongButton.setOnClickListener {
            //get the fields from form
            val title = songTitleET.text.toString()
            val artist = songArtistET.text.toString()
            val album = songAlbumET.text.toString()
            //assign to a book model
            val song = Song(title = title, artist = artist, album = album)
            //save it to database
            if(databaseHandler.create(song)) {
                Toast.makeText(this, "Song was added", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
            else {
                Toast.makeText(this, "Oops something went wrong.", Toast.LENGTH_SHORT).show()
            }
            clearFields()
        }
    }
    fun clearFields(){
        songTitleET.text.clear()
        songArtistET.text.clear()
        songAlbumET.text.clear()
    }
}

