package com.example.info_h2000_ax

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

class Balle (x1: Float, y1: Float, val diametre: Float) {

    val random = Random()
    val paint = Paint()
    val r= RectF(x1, y1, x1 + diametre, y1 + diametre)
    var color = Color.argb(255, random.nextInt(256),random.nextInt(256), random.nextInt(256))

    fun draw(canvas: Canvas?) {
        paint.color = color
        canvas?.drawOval(r, paint)
    }
}