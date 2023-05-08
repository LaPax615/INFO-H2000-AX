package com.example.info_h2000_ax

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Cell (x1: Float, y1: Float, val diametre: Float, val contraintes: String, var par: Float) {
    val random = Random()
    val paint = Paint()
    val r= RectF(x1, y1, x1 + diametre, y1 + diametre)
    //var color = Color.argb(255, random.nextInt(256),random.nextInt(256), random.nextInt(256))
    var color = Color.argb(255, 0, 0, 0)

    var showText = false
    var x = x1
    var y = y1

    fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

    }


    fun draw(canvas: Canvas?) {
        paint.color = color
        var v = (diametre/12).toInt() * 2
        paint.strokeWidth = diametre / v


        //println("Specific Mur: ${contraintes}")

        val canvasWidth = canvas?.width ?: 0
        val canvasHeight = canvas?.height ?: 0



        //canvas?.drawOval(r, paint)
        //canvas?.drawRect(r, paint)
        val startX = x - diametre / 2
        val startY = y - diametre / 2
        val endX = x + diametre / 2
        val endY = y + diametre / 2

        //val firstChar = contraintes.get(0)

        if (contraintes.get(0) == '1') {
            //println("True")
            canvas?.drawLine(startX, startY, endX, startY, paint)
        }else{
            //println("False")
        }

        if (contraintes.get(1) == '1') {
            //println("True")
            canvas?.drawLine(endX, startY, endX, endY, paint)
        }else{
            //println("False")
        }

        if (contraintes.get(2) == '1') {
            //println("True")
            canvas?.drawLine(startX, endY, endX, endY, paint)
        }else{
            //println("False")
        }

        if (contraintes.get(3) == '1') {
            //println("True")
            canvas?.drawLine(startX, startY, startX, endY, paint)
        }else{
            //println("False")
        }



    }


}