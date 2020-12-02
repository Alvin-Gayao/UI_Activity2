package com.example.uielementsact2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.uielementsact2.models.Album
import com.example.uielementsact2.models.Song
import java.sql.Date

class AlbumsTableHandler (var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "albums_database"
        private val TABLE_NAME = "albums"
        private val COL_ID = "id"
        private val COL_TITLE = "title"
        private val COL_DATE = "release_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //TODO("Not yet implemented")
        //define query
        val query = "CREATE TABLE "+TABLE_NAME+" ("+COL_ID+" INTEGER PRIMARY KEY, "+COL_TITLE+
                "  TEXT, "+COL_DATE+" TEXT)"
        //execute
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {
        //TODO("Not yet implemented")
        db!!.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME)
        onCreate(db)
    }
    fun create(album: Album):Boolean{
        //get the database
        val database = this.writableDatabase
        //get content value
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, album.title)
        contentValues.put(COL_DATE, album.release_date)
        //insert
        val  result = database.insert(TABLE_NAME, null, contentValues)
        //check result
        if(result == (0).toLong()){
            return false
        }
        return true
    }
    fun update(album: Album):Boolean{
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, album.title)
        contentValues.put(COL_DATE, album.release_date)
        val  result = database.update(TABLE_NAME, contentValues,"id = "+album.id, null)

        if(result == 0){
            return false
        }
        return true
    }

    fun read(): MutableList<Album>{
        val albumsList : MutableList<Album> = ArrayList()
        val query = "SELECT * FROM "+TABLE_NAME
        val database = this.readableDatabase
        var cursor : Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        }catch (e: SQLException){
            return albumsList
        }
        var id : Int
        var title : String
        var release_date : String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                release_date = cursor.getString(cursor.getColumnIndex(COL_DATE))

                val album = Album(id, title, release_date)
                albumsList.add(album)
            }while (cursor.moveToNext())
        }
        return albumsList
    }

    fun readOne(album_id: Int): Album {
        var album : Album = Album(0, "", "")
        val query = "SELECT * FROM ${AlbumsTableHandler.TABLE_NAME} WHERE id = $album_id"
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLException) {
            return album
        }
        var id: Int
        var title: String
        var release_date: String

        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(AlbumsTableHandler.COL_ID))
            title = cursor.getString(cursor.getColumnIndex(AlbumsTableHandler.COL_TITLE))
            release_date = cursor.getString(cursor.getColumnIndex(AlbumsTableHandler.COL_DATE))

            album = Album(id, title, release_date)
        }
        return album
    }
}