package com.example.uielementsact2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.uielementsact2.models.Song

class SongsTableHandler (var context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "songs_database"
        private val TABLE_NAME = "songs"
        private val COL_ID = "id"
        private val COL_TITLE = "title"
        private val COL_ARTIST = "artist"
        private val COL_ALBUM = "album"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //TODO("Not yet implemented")
        //define query
        val query = "CREATE TABLE "+TABLE_NAME+" ("+COL_ID+" INTEGER PRIMARY KEY, "+COL_TITLE+
                "  TEXT, "+COL_ARTIST+" TEXT, "+COL_ALBUM+" TEXT)"
        //execute
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {
        //TODO("Not yet implemented")
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun create(song: Song):Boolean{
        //get the database
        val database = this.writableDatabase
        //get content value
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, song.title)
        contentValues.put(COL_ARTIST, song.artist)
        contentValues.put(COL_ALBUM, song.album)
        //insert
        val  result = database.insert(TABLE_NAME, null, contentValues)
        //check result
        if(result == (0).toLong()){
            return false
        }
        return true
    }
    fun update(song: Song):Boolean{
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, song.title)
        contentValues.put(COL_ARTIST, song.artist)
        contentValues.put(COL_ALBUM, song.album)
        val  result = database.update(TABLE_NAME, contentValues,"id = "+song.id, null)

        if(result == 0){
            return false
        }
        return true
    }
    fun delete(song : Song): Boolean{
        val database = this.writableDatabase
        val result = database.delete(TABLE_NAME, "id = ${song.id}", null)
        if (result == 0){
            return false
        }
        return true
    }

    fun read(): MutableList<Song>{
        val songsList : MutableList<Song> = ArrayList()
        val query = "SELECT * FROM "+ TABLE_NAME
        val database = this.readableDatabase
        var cursor : Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        }catch (e: SQLException){
            return songsList
        }
        var id : Int
        var title : String
        var artist : String
        var album : String
        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                artist = cursor.getString(cursor.getColumnIndex(COL_ARTIST))
                album = cursor.getString(cursor.getColumnIndex(COL_ALBUM))
                val song = Song(id, title, artist, album)
                songsList.add(song)
            }while (cursor.moveToNext())
        }
        return songsList
    }
    fun readOne(song_id: Int): Song {
        var song: Song = Song(0, "", "", "")
        val query = "SELECT * FROM $TABLE_NAME WHERE id = $song_id"
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery(query, null)
        } catch (e: SQLException) {
            return song
        }
        var id: Int
        var title: String
        var artist: String
        var album: String
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(SongsTableHandler.COL_ID))
            title = cursor.getString(cursor.getColumnIndex(SongsTableHandler.COL_TITLE))
            artist = cursor.getString(cursor.getColumnIndex(SongsTableHandler.COL_ARTIST))
            album = cursor.getString(cursor.getColumnIndex(SongsTableHandler.COL_ALBUM))
            song = Song(id, title, artist, album)
        }
        return song
    }
}