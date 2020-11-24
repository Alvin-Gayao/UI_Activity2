package com.example.uielementsact2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class SongsQueueActivity : AppCompatActivity() {
    //notification variables
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notification"
    private val description = "Test Notification"
    //array adapter
    private val adapter : ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs_queue)
        //initialize notification manager
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedSong)
        val listSongView = findViewById<ListView>(R.id.songListView)
        listSongView.adapter = adapter
        registerForContextMenu(listSongView)

        if(listSongView.adapter?.count == 0){
            val intent = Intent(applicationContext, SongsQueueActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(applicationContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                notificationChannel = NotificationChannel(
                    channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this,channelId)
                    .setContentTitle("Notification Example")
                    .setContentText("This is a notification message")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
            } else {
                builder = Notification.Builder(this)
                    .setContentTitle("Notification Example")
                    .setContentText("This is a notification message")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234,builder.build())
            val toast = Toast.makeText(applicationContext, "Empty List", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?,v: View?,menuInfo: ContextMenu.ContextMenuInfo?)
    {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.songs_queue_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.song_delete -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                selectedSong.removeAt(info.position)
                adapter?.notifyDataSetChanged()
                startActivity(Intent(this, SongsQueueActivity::class.java))
                val toast = Toast.makeText(this, "Song removed from list", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return super.onContextItemSelected(item)
    }
}