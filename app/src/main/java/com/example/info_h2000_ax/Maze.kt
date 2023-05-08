package com.example.info_h2000_ax


import java.util.*

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

        val wallsMatrix = Array(numRows) { Array(numCols) { "" } }
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

        return wallsMatrix

    }

}