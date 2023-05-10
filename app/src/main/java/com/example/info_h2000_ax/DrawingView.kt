package com.example.info_h2000_ax

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

import kotlin.random.Random

class DrawingView @JvmOverloads constructor (context: Context, private var dimension: Int, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes, defStyleAttr) {

    lateinit var canvas: Canvas







    var canvasWidth = 100

    val backgroundPaint = Paint()

    val screenWidth = resources.displayMetrics.widthPixels

    var param = (screenWidth/(dimension+1)).toFloat()

    //var maze = Maze(dimension, dimension, param)

    val textPaint = Paint()

    var Gen = Generator(dimension, param) //composition

    var LesData = Gen.generateMaze()  //maze details



    var IntWinX = Random.nextInt(1, dimension)
    var IntWinY = Random.nextInt(1, dimension)

    var WinX = (IntWinX) * (param).toFloat()
    var WinY = (IntWinY) * (param).toFloat()



    var Arbitre = ControllingParties()


    init {
        backgroundPaint.color = Color.WHITE
        textPaint.textSize = (screenWidth/20).toFloat()
        textPaint.color = Color.BLUE
        Arbitre.changeRound()

    }
    var round = Arbitre.round


    var active1 = 1
    var active2 = 0

    var P1 = Player(2,2, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dimension), "DOWNER", dimension, Color.argb(255, 0, 0, 255), active1) //simple communication ou association
    var P2 = Player(2,2, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dimension), "DOWNER", dimension, Color.argb(255, 135, 255, 0), active2)
    var win1 = false
    var win2 = false



    fun reset(dim: Int) {
        param = (screenWidth / (dim + 1)).toFloat()
        //maze = Maze(dim, dim, param)
        Gen = Generator(dim, param) //composition

        LesData = Gen.generateMaze()
        IntWinX = Random.nextInt(1, dim)
        IntWinY = Random.nextInt(1, dim)
        WinX = (IntWinX) * (param).toFloat()
        WinY = (IntWinY) * (param).toFloat()

        if (active1 == 0) {
            active1 = 1
        } else {
            active1 = 0
        }

        if (active2 == 0) {
            active2 = 1
        } else {
            active2 = 0
            active1 = 0
        }



        P1 = Player(1, 1, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dim), "UPPER", dim, Color.argb(255, 0, 0, 255), active1) //simple communication ou association
        P2 = Player(2, 2, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dim), "UPPER", dim, Color.argb(255, 135, 255, 0), active2) //simple communication ou association

    }


    fun new_maze() {

        param = (screenWidth / (dimension + 1)).toFloat()
        //maze = Maze(dim, dim, param)
        Gen = Gen //composition

        LesData = LesData

        if (active1 == 0) {
            active1 = 1
        } else {
            active1 = 0
        }

        if (active2 == 0) {
            active2 = 1
        } else {
            active2 = 0
            active1 = 0
        }

        P1 = Player(1, 1, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dimension), "UPPER", dimension, Color.argb(255, 0, 0, 255), active1) //simple communication ou association
        P2 = Player(2, 2, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dimension), "UPPER", dimension, Color.argb(255, 135, 255, 0), active2) //simple communication ou association

    }


    fun evaluateWin() {
        if (active1 == 1) {
            if (P1.get_position_x() == WinX && P1.get_position_y() == WinY) {
                // Player has reached the win cell
                println("YOU WIN SAID BY DRAWINGVIEW")

                //dimension = dimension + 1
                //Arbitre.changeRound()

                var Mouvements = P1.get_mouvements()
                Arbitre.changePlayer1Mov(P1.get_mouvements())

               new_maze()

            } else {
                println("NOT YET WON SAID BY DRAWINGVIEW")
            }
        }else if (active2 == 1) {
            if (P2.get_position_x() == WinX && P2.get_position_y() == WinY) {
                // Player has reached the win cell
                println("YOU WIN SAID BY DRAWINGVIEW")


                Arbitre.changePlayer2Mov(P2.get_mouvements())

                new_maze()


            } else {
                println("NOT YET WON SAID BY DRAWINGVIEW")
            }
        }

    }




    fun player_up() {
        P1.go_up()
        P2.go_up()
        evaluateWin()
        //DrawingView.invalidate
    }

    fun player_down() {
        P1.go_down()
        P2.go_down()
        evaluateWin()
    }

    fun player_right() {
        P1.go_right()
        P2.go_right()
        evaluateWin()
    }

    fun player_left() {
        P1.go_left()
        P2.go_left()
        evaluateWin()
    }

    fun player_reset() {

        dimension = dimension + 1
        reset(dimension)
        active1 = 1
        active2 = 0



        P1 = Player(2,2, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dimension), "UPPER", dimension, Color.argb(255, 0, 0, 255), active1) //simple communication ou association
        P2 = Player(2,2, WinX, WinY, (param).toFloat(), Gen.returnContraintsMatrix(LesData, dimension), "UPPER", dimension, Color.argb(255, 135, 255, 0), active2) //simple communication ou association


        //P1.go_to_start()
        //P2.go_to_start()
    }



    var bgimg = "C:/Users/kshej/AndroidStudioProjects/INFOH2000AX/app/src/main/java/com/example/info_h2000_ax/Grass.jpg"
    private val backgroundBitmap = BitmapFactory.decodeFile(bgimg)


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        backgroundPaint.color = Color.WHITE

        canvasWidth = (canvas?.width ?: 0) as Int

        canvas?.drawRect(0F, 0F, width.toFloat(), height.toFloat(), backgroundPaint)

        if (backgroundBitmap != null && !backgroundBitmap.isRecycled) {
            canvas?.drawBitmap(backgroundBitmap, 0f, 0f, null)
        }

        if (active1 == active2) {
            canvas?.drawColor(Color.RED)
            var paint = Paint()
            paint.color = Color.BLUE
            paint.textSize = 100f
            //var message = "Winner found"
            var message = Arbitre.broadcastWinner()
            // Draw message
            canvas?.drawText(message, canvas.width / 4f, canvas.height / 3f, paint)
        }else {

            for (data in LesData) {
                var x = data.getx()
                var y = data.gety()
                var WinCell = false

                if (x == WinX && y == WinY) {
                    WinCell = true
                    var murs = data.getwalls()
                    val b = Cell(data, (param * 1.0).toFloat(), WinCell)
                    b.draw(canvas)
                } else {
                    var murs = data.getwalls()
                    val b = Cell(data, (param * 1.0).toFloat(), WinCell)
                    b.draw(canvas)
                }


            }
            P1.draw(canvas)
            P2.draw(canvas)
        }


    }

}


