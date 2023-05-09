package com.example.info_h2000_ax

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Cell (var data: CellData, val SideLength: Float):ElementDessinable { //Inherite d'Element dessinable et est composé de CellData

    //var x = data.getx()
    //var y = data.gety()
    //data.modifwalls(output[(y/param).toInt() - 1][(x/param).toInt() - 1])
    //var murs = data.getwalls()


    private val PosX = data.getx()
    private val PosY = data.gety()
    var contraintes = data.getwalls() //In case we want to add say superpowers to go across walls

    override var paint = Paint()
    override val r = RectF(PosX, PosY, PosX + SideLength, PosY + SideLength)
    override val random = Random()

    var color = Color.argb(255, 0, 0, 0)

    private val startX = PosX - SideLength / 2
    private val startY = PosY - SideLength / 2
    private val endX = PosX + SideLength / 2
    private val endY = PosY + SideLength / 2

    var v = (SideLength/12).toInt() * 2
    override fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    override fun draw(canvas: Canvas?) {
        paint.color = color
        paint.strokeWidth = SideLength / v

        if (contraintes.get(0) == '1') {
            canvas?.drawLine(startX, startY, endX, startY, paint)
        }else{
            //println("False")
        }

        if (contraintes.get(1) == '1') {

            canvas?.drawLine(endX, startY, endX, endY, paint)
        }else{
            //println("False")
        }

        if (contraintes.get(2) == '1') {

            canvas?.drawLine(startX, endY, endX, endY, paint)
        }else{
            //println("False")
        }

        if (contraintes.get(3) == '1') {

            canvas?.drawLine(startX, startY, startX, endY, paint)
        }else{
            //println("False")
        }
    }
}