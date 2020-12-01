package com.example.uielementsact2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementsact2.models.Song

class EditSongActivity : AppCompatActivity() {
    lateinit var updateSongButton: Button
    lateinit var editSongTitleET: EditText
    lateinit var editSongArtistET: EditText
    lateinit var editSongAlbumET: EditText
    lateinit var song : Song
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)

        val song_id = intent.getIntExtra("song_id", 0)

        //get record from database
        val databaseHandler = SongsTableHandler(this)
        song = databaseHandler.readOne(song_id)

        editSongTitleET = findViewById(R.id.editSongTitleET)
        editSongArtistET = findViewById(R.id.editSongArtistET)
        editSongAlbumET = findViewById(R.id.editSongAlbumET)
        updateSongButton = findViewById(R.id.updateSongButton)

        editSongTitleET.setText(song.title)
        editSongArtistET.setText(song.artist)
        editSongAlbumET.setText(song.album)

        updateSongButton.setOnClickListener {
            //get the fields from form
            val title = editSongTitleET.text.toString()
            val artist = editSongArtistET.text.toString()
            val album = editSongAlbumET.text.toString()
            //assign to a book model
            val updated_book = Song(id = song.id, title = title, artist = artist, album = album)
            //save it to database
            if(databaseHandler.update(updated_book)) {
                Toast.makeText(this, "Song was edited", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
            else {
                Toast.makeText(this, "Oops something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}