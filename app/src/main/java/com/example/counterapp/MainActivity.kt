package com.example.counterapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private var clickCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val counterView: TextView = findViewById(R.id.counter)
        val body: ConstraintLayout = findViewById(R.id.body)
        val resetButton: Button = findViewById(R.id.resetButton)
        val sharedPreference = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        clickCount = sharedPreference.getInt(COUNT_KEY, 0)
        counterView.text = clickCount.toString()
        body.setOnClickListener {
            clickCount++
            // update to a livedata with view model later
            counterView.text = clickCount.toString()
        }
        resetButton.setOnClickListener {
            clickCount = 0;
            counterView.text = clickCount.toString()// remove when livedata used.
        }

    }

    override fun onPause() {
        super.onPause()
        val sharedPreference = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt(COUNT_KEY, clickCount)
        editor.apply()
    }
    companion object {
        const val SHARED_PREFERENCE_NAME = "counter_storage"
        const val COUNT_KEY = "count_key"
    }
}