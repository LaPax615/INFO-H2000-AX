package com.example.info_h2000_ax

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow

class Player(kx: Int, ly: Int, var winX: Float, var winY:Float, par: Float, val mazeData: Array<Array<String>>, var powers: String, var dim: Int, var BallColor: Int, var operating: Int): ElementVariable(kx, ly, par, powers, dim, operating), ElementDessinable {

    //private val winObservable = Observable()

    override var paint = Paint()
    var param = par*0.75
    //var x = par*kx
    //var y = par*ly

    //override val random = Random()
    override val r = RectF((x - param/2).toFloat(), (y - param/2).toFloat(), (x + par/2).toFloat(), (y + par/2).toFloat())

    var winXX = winX * par
    var winYY = winY * par
    var sx = (x/par).toInt()
    var sy = (y/par).toInt()

    override var color = BallColor

    val initial_x = x
    val initial_y = y
    var stepsX = 0
    var stepsY = 0

    var situation = ""
    var win = false

    override fun get_contraintes():String {
        //var PosX = sx
        var PosX = (x/par).toInt()
        var PosY = (y/par).toInt()

        //var PosY = sy
        var Matrix = mazeData
        var contraintes = mazeData[PosX - 1][PosY - 1]
        //println("Contraintes ${PosX - 1} ${PosY - 1} ${contraintes}")
        return contraintes
    }

    override fun evaluateWin() {
        if (x == winX && y == winY) {
            // Player has reached the win cell
            println("YOU WIN")
            situation = "YOU WON"
            win = true

            // Create a new AlertDialog to display the message and restart button

        } else {
            println("NOT YET WON")
            situation = ""
            win = false
        }
    }

    override fun evaluateWin2(mainActivity: MainActivity) {
        if (x == winX && y == winY) {
            // Player has reached the win cell
            println("YOU WIN")
            situation = "YOU WON"

            // Create a new AlertDialog to display the message and restart button
            showPopup(mainActivity)

        } else {
            println("NOT YET WON")
            situation = ""
        }
    }

    fun showPopup(mainActivity: MainActivity) {
        val inflater = mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.win_popup, null)

        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

        val restartButton = popupView.findViewById<Button>(R.id.restart_button)
        restartButton.setOnClickListener {
            // restart the game or activity here
            mainActivity.recreate()
            popupWindow.dismiss()
        }
    }



    fun get_situation(): String {
        return situation
    }

    override fun go_to_start() {
        x = initial_x
        y = initial_y
    }

    override fun draw(canvas: Canvas?) {
        paint.color = color

        r.set((x - param/2).toFloat(), (y - param/2).toFloat(), (x + param/2).toFloat(), (y + param/2).toFloat())
        canvas?.drawOval(r, paint)


    }

}