package com.example.info_h2000_ax

import android.graphics.*
import java.util.*

class Cell (var data: CellData, val SideLength: Float, var WinCell: Boolean):ElementDessinable { //Inherite d'Element dessinable et est composé de CellData

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

    override var color = Color.argb(255, 123, 63, 0)

    //override var color = Color.

    private val startX = PosX - SideLength / 2
    private val startY = PosY - SideLength / 2
    private val endX = PosX + SideLength / 2
    private val endY = PosY + SideLength / 2

    var v = (SideLength/18).toInt()
    /*override fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }*/

    val starPoints = arrayOf(
        floatArrayOf(1f, 3f), floatArrayOf(2f, 3f),
        floatArrayOf(2.5f, 1.5f), floatArrayOf(3f, 3f),
        floatArrayOf(4f, 3f), floatArrayOf(3.5f, 4f),
        floatArrayOf(4f, 5f), floatArrayOf(2.5f, 4.5f),
        floatArrayOf(1f, 5f), floatArrayOf(1.5f, 4f)
    )




    override fun draw(canvas: Canvas?) {
        paint.color = color
        paint.strokeWidth = SideLength / v

        if (WinCell == true) {
            paint.color = Color.GREEN
            paint.strokeWidth = (paint.strokeWidth * 1.25).toFloat()
            paint.style = Paint.Style.FILL

            canvas?.drawCircle(PosX, PosY, SideLength/2, paint)

        }




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