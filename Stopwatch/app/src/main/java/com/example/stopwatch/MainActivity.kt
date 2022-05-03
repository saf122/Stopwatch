package com.ProjectGurukul.stopwatch

import android.R
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {
    private var sec = 0
    private var is_running = false
    private var was_running = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("seconds")
            is_running = savedInstanceState.getBoolean("running")
            was_running = savedInstanceState.getBoolean("wasRunning")
        }
        running_Timer()
    }

    public override fun onSaveInstanceState(
        savedInstanceState: Bundle
    ) {
        savedInstanceState.putInt("seconds", sec)
        savedInstanceState.putBoolean("running", is_running)
        savedInstanceState.putBoolean("wasRunning", was_running)
    }

    override fun onPause() {
        super.onPause()
        was_running = is_running
        is_running = false
    }

    override fun onResume() {
        super.onResume()
        if (was_running) {
            is_running = true
        }
    }

    fun onClickStart(view: View?) {
        is_running = true
    }

    fun onClickStop(view: View?) {
        is_running = false
    }

    fun onClickReset(view: View?) {
        is_running = false
        sec = 0
    }

    private fun running_Timer() {
        val t_View = findViewById<View>(R.id.time_view) as TextView
        val handle = Handler()
        handle.post(object : Runnable {
            override fun run() {
                val hrs = sec / 3600
                val mins = sec % 3600 / 60
                val secs = sec % 60
                val time_t =
                    String.format(Locale.getDefault(), "    %d:%02d:%02d   ", hrs, mins, secs)
                t_View.text = time_t
                if (is_running) {
                    sec++
                }
                handle.postDelayed(this, 1000)
            }
        })
    }
}