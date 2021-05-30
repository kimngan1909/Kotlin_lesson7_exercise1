package com.gmail.nguyenkimngan190920.date23_04

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.MediaController
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var timePicker: TimePicker
    lateinit var player: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val setBtn = findViewById<Button>(R.id.setAlarmBtn)
        val stopBtn = findViewById<Button>(R.id.stopAlarmBtn)
        timePicker = findViewById<TimePicker>(R.id.timePicker)
        player = MediaPlayer.create(this,R.raw.alarm_mario)
        setBtn.setOnClickListener{
            checkTimer()
        }
        stopBtn.setOnClickListener {
            player.stop()
        }
    }
    private fun checkTimer(){
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object:Runnable{
            override fun run() {
                val hour = timePicker.hour
                val minute = timePicker.minute
                val calendar = Calendar.getInstance()
                val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
                val currentMinute = calendar.get(Calendar.MINUTE)
                if(hour == currentHour && minute == currentMinute){
                    player.start()
                }
                else{
                    mainHandler.postDelayed(this,1000)
                }
            }
        })
    }
}