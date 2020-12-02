package com.example.uielementsact2

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.uielementsact2.models.Album
import com.example.uielementsact2.models.Song
import kotlinx.android.synthetic.main.activity_add_album.*
import java.sql.Date
import java.util.*

class AddAlbumActivity : AppCompatActivity() {
    lateinit var addAlbumButton : Button
    lateinit var albumTitleET : EditText
    lateinit var albumReleaseTV : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album)
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

        val databaseHandler = AlbumsTableHandler(this)
        albumTitleET = findViewById(R.id.albumTitleET)
        albumReleaseTV = findViewById(R.id.dateTextView)

        addAlbumButton = findViewById(R.id.addAlbumButton)
        addAlbumButton.setOnClickListener {
            //get the fields from form
            val title = albumTitleET.text.toString()
            val release_date = albumReleaseTV.text.toString()
            //assign to a book model
            val album = Album(title = title,  release_date = release_date)
            //save it to database
            if(databaseHandler.create(album)) {
                Toast.makeText(this, "Album was added", Toast.LENGTH_SHORT).show()
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