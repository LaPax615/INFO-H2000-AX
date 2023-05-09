package com.example.info_h2000_ax

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceView
import java.util.*

import kotlin.random.Random

class DrawingView @JvmOverloads constructor (context: Context, private val dimension: Int, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes, defStyleAttr) {

    lateinit var canvas: Canvas

    var canvasWidth = 100

    val backgroundPaint = Paint()
    //val random = Random()

    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val dpiX = displayMetrics.xdpi


    var param = (screenWidth/(dimension+1)).toFloat()


    var maze = Maze(dimension, dimension, param)

    val textPaint = Paint()

    val Gen = Generator(maze, dimension, param) //composition

    var LesData = Gen.generateMaze()  //maze details

    //var WinX = 3 * (param).toFloat()
    //var WinY = 3 * (param).toFloat()

    var IntWinX = Random.nextInt(1, dimension)
    var IntWinY = Random.nextInt(1, dimension)

    var WinX = (IntWinX) * (param).toFloat()
    var WinY = (IntWinY) * (param).toFloat()
    //var WinX = Float
    //var WinY = Float

    var P = Player(2,3, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dimension)) //simple communication ou association
    var Movements = Gen.PathFinder(Gen.returnContraintsMatrix(LesData, dimension), 2, 3, IntWinX, IntWinY)



    init {
        backgroundPaint.color = Color.WHITE
        textPaint.textSize = (screenWidth/20).toFloat()
        textPaint.color = Color.BLUE

    }



    fun new_maze(dim: Int) {
        maze = Maze(dim, dim, param)
    }


    fun get_dimension(): Int {
        return dimension.toInt()
    }

    fun player_up() {
        P.go_up()
    }

    fun player_down() {
        P.go_down()
    }

    fun player_right() {
        P.go_right()
    }

    fun player_left() {
        P.go_left()
    }

    fun player_reset() {
        P.go_to_start()
    }



    fun getPixelFromPercentOfCanvasWidth(canvas: Canvas?, percent: Float): Float {
        val canvasWidth = canvas?.width ?: 0
        return (percent / 100f) * canvasWidth
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        backgroundPaint.color = Color.WHITE

        canvasWidth = (canvas?.width ?: 0) as Int

        canvas?.drawRect(0F, 0F, width.toFloat(), height.toFloat(), backgroundPaint)

        //val situ = P.get_situation()
        //canvas?.drawText("Situation: $situ ", 30f, 50f, textPaint)



        for (data in LesData) {
            var x = data.getx()
            var y = data.gety()
            var WinCell = false

            if (x == WinX && y == WinY) {
                WinCell = true
                var murs = data.getwalls()
                val b = Cell(data, (param*1.0).toFloat(), WinCell)
                b.draw(canvas)
            }else{
                var murs = data.getwalls()
                val b = Cell(data, (param*1.0).toFloat(), WinCell)
                b.draw(canvas)
            }

            //data.modifwalls(output[(y/param).toInt() - 1][(x/param).toInt() - 1])

        }
        P.draw(canvas)


    }

}


