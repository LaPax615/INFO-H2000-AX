package com.example.info_h2000_ax

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Player (kx: Int, ly: Int, var winX: Int, var winY:Int, par: Float, val mazeData: Array<Array<String>>): ElementVariable(kx, ly, par), ElementDessinable {

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

    //override var color = Color.argb(255, 255, 0, 0)

    val initial_x = x
    val initial_y = y
    var stepsX = 0
    var stepsY = 0

    var situation = ""

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

        var para = par


        if ((x/par).toInt() == winX && (y/par).toInt() == winY) {
            println("YOU WIN")
            situation = "YOU WON"

        }else{
            println("NOT YET WON")
            situation = ""
        }

    }

    fun get_situation(): String {
        return situation
    }

    override fun go_to_start() {
        x = initial_x
        y = initial_y
    }


    override fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

    }

    override fun draw(canvas: Canvas?) {
        paint.color = color

        r.set((x - param/2).toFloat(), (y - param/2).toFloat(), (x + param/2).toFloat(), (y + param/2).toFloat())
        canvas?.drawOval(r, paint)


    }

}