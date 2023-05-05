package com.example.info_h2000_ax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    //val displayMetrics = resources.displayMetrics
    //val dpiX = displayMetrics.xdpi
    //val dpiY = displayMetrics.ydpi



    lateinit var drawingView: DrawingView

    fun onClickUP(v: View) {
        drawingView.player_up()
        drawingView.invalidate()
        println("Up Clicked")
    }

    fun onClickDOWN(v: View) {
        drawingView.player_down()
        drawingView.invalidate()
        println("Down Clicked")
    }

    fun onClickRIGHT(v: View) {
        drawingView.player_right()
        drawingView.invalidate()
        println("Right Clicked")
    }

    fun onClickLEFT(v: View) {
        drawingView.player_left()
        drawingView.invalidate()
        println("Left Clicked")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById<DrawingView>(R.id.vMain)
        drawingView.setWillNotDraw(false)
        drawingView.invalidate()

    }
}