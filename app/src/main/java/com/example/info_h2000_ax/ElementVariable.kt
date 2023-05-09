package com.example.info_h2000_ax

import android.graphics.Color
import java.util.*

abstract class ElementVariable(var kx: Int, var ly: Int, val par: Float) {
    /*abstract fun get_contraintes(): String

    abstract fun go_up()

    abstract fun go_down()

    abstract fun go_left()

    abstract fun go_right()

    abstract fun changeCouleur()*/
    var x = par*kx
    var y = par*ly
    var color = Color.argb(255, 255, 0, 0)
    val random = Random()

    abstract fun get_contraintes():String



    abstract fun evaluateWin()




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

    abstract fun go_to_start()

}