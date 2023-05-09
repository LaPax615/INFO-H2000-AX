package com.example.info_h2000_ax


import java.util.*
import kotlin.random.Random

class Maze(val numRows: Int, val numCols: Int, val parameter: Float) {


    private val maze = Array(numRows) { Array(numCols) { Cell() } }
    var par = parameter
    var dim = numRows

    private inner class Cell(var visited: Boolean = false,
                             var leftWall: Boolean = true,
                             var rightWall: Boolean = true,
                             var topWall: Boolean = true,
                             var bottomWall: Boolean = true)

    fun generate() {
        val stack = Stack<Pair<Int, Int>>()
        var curr = Pair(0, 0)
        maze[0][0].visited = true
        stack.push(curr)

        while (!stack.isEmpty()) {
            curr = stack.peek()
            val neighbors = getNeighbors(curr.first, curr.second)
            if (neighbors.isEmpty()) {
                stack.pop()
            } else {
                val next = neighbors[(Math.random() * neighbors.size).toInt()]
                removeWall(curr, next)
                maze[next.first][next.second].visited = true
                stack.push(next)
            }
        }
    }

    private fun getNeighbors(row: Int, col: Int): List<Pair<Int, Int>> {
        val neighbors = mutableListOf<Pair<Int, Int>>()
        if (col > 0 && !maze[row][col-1].visited) neighbors.add(Pair(row, col-1))
        if (col < numCols-1 && !maze[row][col+1].visited) neighbors.add(Pair(row, col+1))
        if (row > 0 && !maze[row-1][col].visited) neighbors.add(Pair(row-1, col))
        if (row < numRows-1 && !maze[row+1][col].visited) neighbors.add(Pair(row+1, col))
        return neighbors
    }

    private fun removeWall(curr: Pair<Int, Int>, next: Pair<Int, Int>) {
        if (curr.first == next.first) { // horizontal move
            if (curr.second < next.second) {
                maze[curr.first][curr.second].rightWall = false
                maze[next.first][next.second].leftWall = false
            } else {
                maze[curr.first][curr.second].leftWall = false
                maze[next.first][next.second].rightWall = false
            }
        } else { // vertical move
            if (curr.first < next.first) {
                maze[curr.first][curr.second].bottomWall = false
                maze[next.first][next.second].topWall = false
            } else {
                maze[curr.first][curr.second].topWall = false
                maze[next.first][next.second].bottomWall = false
            }
        }
    }


    fun printMaze2() {
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                print("+")
                if (maze[row][col].topWall) print("---") else print("   ")
            }
            println("+")
            for (col in 0 until numCols) {
                if (maze[row][col].leftWall) print("|") else print(" ")
                print("   ")
                if (col == numCols-1) print("|")
            }
            println()
        }
        for (col in 0 until numCols) print("+---")
        println("+")
    }



    fun printMaze() {
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                val cell = maze[row][col]
                val walls = "${if (cell.topWall) 1 else 0}${if (cell.rightWall) 1 else 0}${if (cell.bottomWall) 1 else 0}${if (cell.leftWall) 1 else 0}"
                print("$walls ")
            }
            println()
        }
    }

    fun returnWalls(): Array<Array<String>> {
        generate()

        //var numRows = 5
        //var numCols = 5

        var wallsMatrix = Array(numRows) { Array(numCols) { "" } }
        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                val cell = maze[row][col]
                val walls = "${if (cell.topWall) 1 else 0}${if (cell.rightWall) 1 else 0}${if (cell.bottomWall) 1 else 0}${if (cell.leftWall) 1 else 0}"
                wallsMatrix[row][col] = walls
            }
        }
        //println("chau")
        println()
        printMaze()
        println()
        printMaze2()

        /*var counter = 0
        while (counter < numCols+numCols) {
            var x = Random.nextInt(1, numCols-1)
            var y = Random.nextInt(1, numCols-1)
            //var z = Random.nextInt(0, 4)

            var newWall = "7777"

            var cellC = wallsMatrix[x][y]

            var cellU = wallsMatrix[x][y-1]
            var cellD = wallsMatrix[x][y+1]

            var cellL = wallsMatrix[x-1][y]
            var cellR = wallsMatrix[x+1][y]


            var charArrayC = cellC.toCharArray()

            var charArrayU = cellU.toCharArray()
            var charArrayD = cellD.toCharArray()

            var charArrayL = cellL.toCharArray()
            var charArrayR = cellR.toCharArray()

            if (charArrayC[0] == '1') {
                //newWall = newWall + "0"
                charArrayC[0] = '0'
                charArrayU[2] = '0'
            }else{
                charArrayC[0] = '0'
                charArrayU[2] = '0'
            }

            if (charArrayC[1] == '1') {
                //newWall = newWall + "0"
                charArrayC[1] = '0'
                charArrayR[3] = '0'
            }else{
                charArrayC[1] = '0'
                charArrayR[3] = '0'
            }

            if (charArrayC[2] == '1') {
                //newWall = newWall + "0"
                charArrayC[2] = '0'
                charArrayD[0] = '0'
            }else{
                charArrayC[2] = '0'
                charArrayD[0] = '0'
            }

            if (charArrayC[3] == '1') {
                //newWall = newWall + "0"
                charArrayC[3] = '0'
                charArrayL[1] = '0'
            }else{
                charArrayC[3] = '0'
                charArrayL[1] = '0'
            }

            /*if (wall[1] == '1') {
                newWall = newWall + "0"
            }else{
                newWall = newWall + "0"
            }

            if (wall[2] == '1') {
                newWall = newWall + "0"
            }else{
                newWall = newWall + "0"
            }

            if (wall[3] == '1') {
                newWall = newWall + "0"
            }else{
                newWall = newWall + "0"
            }*/

            wallsMatrix[x][y] = String(charArrayC)

            wallsMatrix[x][y-1] = String(charArrayU)
            wallsMatrix[x][y+1] = String(charArrayD)

            wallsMatrix[x-1][y] = String(charArrayL)
            wallsMatrix[x+1][y] = String(charArrayR)


            counter = counter + 1
        }*/





        return wallsMatrix

    }

}