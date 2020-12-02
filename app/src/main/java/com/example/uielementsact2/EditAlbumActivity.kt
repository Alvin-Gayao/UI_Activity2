package com.example.uielementsact2

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.uielementsact2.models.Album
import kotlinx.android.synthetic.main.activity_add_album.*
import kotlinx.android.synthetic.main.activity_add_album.selectDateButton
import kotlinx.android.synthetic.main.activity_edit_album.*
import kotlinx.android.synthetic.main.album_card_view.*
import java.util.*

class EditAlbumActivity : AppCompatActivity() {
    lateinit var updateAlbumButton : Button
    lateinit var editAlbumTitleET : EditText
    lateinit var editAlbumReleaseTV : TextView
    lateinit var album : Album
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_album)

        //calendar
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        selectDateButton.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view,mYear,mMonth,mDay ->
                //set to textview
                dateTextView.setText(""+mDay+"/"+mMonth+"/"+mYear)
            }, year, month, day)
            //show dialog
            dpd.show()
        }
        val album_id = intent.getIntExtra("album_id", 0)

        val databaseHandler = AlbumsTableHandler(this)
        album = databaseHandler.readOne(album_id)
        editAlbumTitleET = findViewById(R.id.editAlbumTitleET)
        editAlbumReleaseTV = findViewById(R.id.editDateTextView)

        editAlbumTitleET.setText(album.title)
        editAlbumReleaseTV.setText(album.release_date)

        updateAlbumButton = findViewById(R.id.updateAlbumButton)
        addAlbumButton.setOnClickListener {
            //get the fields from form
            val title = albumTitleET.text.toString()
            val release_date = editAlbumReleaseTV.text.toString()
            //assign to a album model
            val updated_album = Album(title = title,  release_date = release_date)
            //save it to database
            if(databaseHandler.create(updated_album)) {
                Toast.makeText(this, "Album was updated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, AlbumsActivity::class.java))
            }
            else {
                Toast.makeText(this, "Oops something went wrong.", Toast.LENGTH_SHORT).show()
            }
            clearFields()
        }
    }
    fun clearFields(){
        albumTitleET.text.clear()
    }
}