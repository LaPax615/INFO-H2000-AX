package com.example.info_h2000_ax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById<DrawingView>(R.id.surfaceView)
        drawingView.setWillNotDraw(false)
        drawingView.invalidate()

    }
}