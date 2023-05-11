package com.example.info_h2000_ax

class CellData (x: Float, y: Float, walls: String) {
    val cx = x
    val cy = y
    var cwalls = walls

    fun getx():Float {
        return cx
    }

    fun gety():Float {
        return cy
    }

    fun getwalls():String {
        return cwalls
    }

    fun modifwalls(wall: String) {
        cwalls = wall
    }
}