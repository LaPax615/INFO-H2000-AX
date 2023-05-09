package com.example.info_h2000_ax

import android.graphics.*
import java.util.*

interface ElementDessinable {

    val random: Random
    var paint: Paint
    val r: RectF

    private fun Int.Companion.toFloat(): Float {
        return Int.Companion.toFloat()
    }

    fun changeCouleur()

    fun draw(canvas: Canvas?) {

    }

}