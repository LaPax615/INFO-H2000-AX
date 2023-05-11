package com.example.info_h2000_ax

import android.graphics.Color
import java.util.*

abstract class ElementVariable(var kx: Int, var ly: Int, protected val par: Float, var power: String, var dimension: Int, var operation: Int) {

    protected var Mouvements = 0
    var x = par*kx
    var y = par*ly
    open var color = Color.argb(255, 255, 0, 0)
    val random = Random()



    fun changeMouvements() {
        Mouvements = Mouvements + 1
    }

    abstract fun get_contraintes():String



    abstract fun evaluateWin()

    abstract fun evaluateWin2(mainActivity: MainActivity)

    fun get_position_x():Float {
        return x
    }

    fun get_position_y():Float {
        return y
    }

    fun get_mouvements():Int{
        return Mouvements
    }

    fun go_up() {
        if (operation == 1) {
            var contraintes = get_contraintes()

            if (power == "UPPER" && y != par) {
                y = (y - par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else if (contraintes.get(0) == '0') {
                y = (y - par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else {
                y = y.toFloat()
                color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
            }

            changeMouvements()


            //evaluateWin()
        }
    }

    fun go_down() {
        if (operation == 1) {
            var contraintes = get_contraintes()


            if (power == "DOWNER" && y != par * dimension) {
                y = (y + par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else if (contraintes.get(2) == '0') {
                y = (y + par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else {
                y = y.toFloat()
                color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
            }

            changeMouvements()
            //evaluateWin()
        }
    }

    fun go_left() {
        if (operation == 1) {

            var contraintes = get_contraintes()


            if (power == "LEFTER" && x != par) {
                x = (x - par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else if (contraintes.get(3) == '0') {
                x = (x - par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else {
                x = x.toFloat()
                color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
            }

            changeMouvements()


        }
        //evaluateWin()
    }

    fun go_right() {

        if (operation == 1) {
            var contraintes = get_contraintes()

            if (power == "RIGHTER" && x != par * dimension) {
                x = (x + par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else if (contraintes.get(1) == '0') {
                x = (x + par).toFloat()
                color =
                    Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            } else {
                x = x.toFloat()
                color = Color.argb(255, 255.toInt(), 0.toInt(), 0.toInt())
            }

            changeMouvements()


        }

        //evaluateWin()
    }

    abstract fun go_to_start()

}