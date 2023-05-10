package com.example.info_h2000_ax

import Timer.Companion.SECONDS_PER_TICK
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import java.util.*
import kotlin.random.Random
import kotlin.text.Typography.dagger


import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel



class MainActivity : AppCompatActivity() {

    private lateinit var player: Player
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

    fun Reset(v: View) {
        drawingView.player_reset()
        drawingView.invalidate()
        println("Left Clicked")
    }



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels




        // Inflate the pop-up layout
        val popupView = layoutInflater.inflate(R.layout.popup_layout, null)

        // Create the AlertDialog to display the pop-up
        val popupDialog = AlertDialog.Builder(this)
            .setView(popupView)
            .create()

        // Set a click listener on the OK button
        popupView.findViewById<Button>(R.id.validate_button).setOnClickListener {
            // Retrieve the input value and close the pop-up
            val dimensionInput = popupView.findViewById<EditText>(R.id.number_input)
            val dimension = dimensionInput.text.toString().toIntOrNull()
            if (dimension != null) {
                drawingView = DrawingView(this, dimension)

                drawingView.setWillNotDraw(false)
                drawingView.invalidate()

                val container = FrameLayout(this)
                container.addView(drawingView)

                // Inflate the button layout and add it to the container
                val buttonLayout = layoutInflater.inflate(R.layout.button_layout, container, false)
                container.addView(buttonLayout)

                // Set the container as the activity content view
                setContentView(container)



                //setContentView(drawingView)
                popupDialog.dismiss()
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }

            /*var param = (screenWidth/(dimension?.plus(1)!!)).toFloat()
            var maze = dimension?.let { it1 -> Maze(it1, dimension, param) }
            val textPaint = Paint()
            val Gen = maze?.let { it1 -> Generator(it1, dimension, param) } //composition
            var LesData = Gen?.generateMaze()  //maze details
            var IntWinX = Random.nextInt(1, dimension)
            var IntWinY = Random.nextInt(1, dimension)
            var WinX = (IntWinX) * (param).toFloat()
            var WinY = (IntWinY) * (param).toFloat()
            player = Gen?.let { it1 ->
                LesData?.let { it2 -> it1.returnContraintsMatrix(it2, dimension) }?.let { it3 ->
                    Player(2,3, WinX, WinY, (param).toFloat(),
                        it3
                    )
                }
            }!!*/


        }
        popupDialog.show()
    }



        /*super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById<DrawingView>(R.id.vMain)
        drawingView.setWillNotDraw(false)
        drawingView.invalidate()*/


}


