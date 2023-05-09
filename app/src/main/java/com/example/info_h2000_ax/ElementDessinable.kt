package com.example.info_h2000_ax

import android.graphics.*
import java.util.*

interface ElementDessinable {

    val random: Random
    var paint: Paint
    val r: RectF
    var color: Int

    private fun Int.Companion.toFloat(): Float {
        return Int.Companion.toFloat()
    }

    fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    fun draw(canvas: Canvas?) {

    }

}