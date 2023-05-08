package com.example.info_h2000_ax

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Player (var kx: Int, var ly: Int, var winX: Int, var winY:Int, val par: Float, val mazeData: Array<Array<String>>){

    //var k = kx
    //var l = ly
    var x = par*kx
    var y = par*ly

    var winXX = winX * par
    var winYY = winY * par

    var sx = (x/par).toInt()
    var sy = (y/par).toInt()

    var param = par*0.75


    val random = Random()
    val paint = Paint()
    val r= RectF((x - param/2).toFloat(), (y - param/2).toFloat(), (x + par/2).toFloat(), (y + par/2).toFloat())

    var color = Color.argb(255, 255, 0, 0)

    val initial_x = x
    val initial_y = y
    var stepsX = 0
    var stepsY = 0

    var situation = ""

    fun get_contraintes():String {
        //var PosX = sx
        var PosX = (x/par).toInt()
        var PosY = (y/par).toInt()

        //var PosY = sy
        var Matrix = mazeData
        var contraintes = mazeData[PosX - 1][PosY - 1]
        //println("Contraintes ${PosX - 1} ${PosY - 1} ${contraintes}")
        return contraintes
    }

    fun evaluateWin() {

        var para = par
        //var dim = DrawingView.get_dimension()


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


    fun go_up() {
        var contraintes = get_contraintes()


        //println("Contraintes sont: ${contraintes}")
        if (contraintes.get(0) == '0') {
            y = (y - par).toFloat()
            color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        }else{
            y = y.toFloat()
            color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
        }
        //println("New position: $kx, $ly")
        evaluateWin()

    }

    fun go_down() {

        var contraintes = get_contraintes()
        //println("Contraintes sont: ${contraintes}")
        if (contraintes.get(2) == '0') {
            y = (y + par).toFloat()
            color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        }else{
            y = y.toFloat()
            color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
        }
        //println("New position: $kx, $ly")



        //y = (y + par).toFloat()
        //color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        evaluateWin()

    }

    fun go_left() {

        var contraintes = get_contraintes()
        //println("Contraintes sont: ${contraintes}")
        if (contraintes.get(3) == '0') {
            x = (x - par).toFloat()
            color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        }else{
            x = x.toFloat()
            color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
        }
        //println("New position: $kx, $ly")


        //x = (x - par).toFloat()
        //color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        evaluateWin()

    }

    fun go_right() {

        var contraintes = get_contraintes()
        //println("Contraintes sont: ${contraintes}")
        if (contraintes.get(1) == '0') {
            x = (x + par).toFloat()
            color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        }else{
            x = x.toFloat()
            color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
        }
        //println("New position: $kx, $ly")
        evaluateWin()


    }

    fun go_to_start() {
        x = initial_x
        y = initial_y
    }


    fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

    }

    fun draw(canvas: Canvas?) {
        paint.color = color
        //paint.strokeWidth = diametre / 10 * 2

        r.set((x - param/2).toFloat(), (y - param/2).toFloat(), (x + param/2).toFloat(), (y + param/2).toFloat())
        canvas?.drawOval(r, paint)


        //canvas?.drawOval(r, paint)
        //canvas?.drawRect(r, paint)

    }

}