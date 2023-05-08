package com.example.info_h2000_ax

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceView
import java.util.*

class DrawingView @JvmOverloads constructor (context: Context, private val dimension: Int, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes, defStyleAttr) {

    lateinit var canvas: Canvas

    var canvasWidth = 100

    val backgroundPaint = Paint()
    val random = Random()

    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val dpiX = displayMetrics.xdpi



    //val dimension = 3
    var param = (screenWidth/(dimension+1)).toFloat()
    //val param = ((200/dimension)*6).toFloat()
    //var param = Float()

    //var param = (canvasWidth/dimension)
    //val canvasWidth = canvas?.width ?: 0

    //val gen = Generator(dimension, param)

    var maze = Maze(dimension, dimension, param)
    val textPaint = Paint()
    //var textPaint.textSize = (screenWidth/20).toInt()


    val Gen = Generator(maze, dimension, param)
    //val LesData = maze.generateMaze()
    var LesData = Gen.generateMaze()
    //val output = maze.returnWalls()
    var P = Player(2,3, 3, 3, (param).toFloat(), Gen.returnContraintsMatrix(maze, dimension))

    //val canvasWidth = getPixelFromPercentOfCanvasWidth(canvas, 50f) // 50% of canvas width in pixels







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

        val situ = P.get_situation()
        canvas?.drawText("Situation: $situ ", 30f, 50f, textPaint)
        //println("Screen Width is: ${screenWidth} ${dpiX}")
        //println("Other Width is: ${canvas?.width} ")
        //maze.printMaze2()

        for (data in LesData) {
            var x = data.getx()
            var y = data.gety()
            //data.modifwalls(output[(y/param).toInt() - 1][(x/param).toInt() - 1])
            var murs = data.getwalls()
            val b = Cell(x, y, (param*1.0).toFloat(), murs, param)
            b.draw(canvas)
        }
        P.draw(canvas)
        //P.get_contraintes()
    }

}


