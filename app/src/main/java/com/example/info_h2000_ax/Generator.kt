package com.example.info_h2000_ax

import kotlin.random.Random


class Generator (val maze: Maze, val dim: Int, val par: Float){

    fun generateBoxes(dim: Int, par: Float): List<CellData> {
        val LesData = mutableListOf<CellData>()

        var par = par.toInt()
        var x = dim
        var y = dim

        var i = 1

        while (i <= x) {
            var j = 1
            while (j <= y) {
                LesData.add(CellData((i * par).toFloat(), (j * par).toFloat(), "1111"))
                //println(j)
                j = j + 1
            }
            i = i + 1
        }

        return LesData
    }

    fun generateMaze(): List<CellData> {
        var param = par
        var dimension = dim

        val LesData = generateBoxes(dimension, param)
        var output = maze.returnWalls() //create random holes in the maze



        for (data in LesData) {
            var x = data.getx()
            var y = data.gety()
            data.modifwalls(output[(y/param).toInt() - 1][(x/param).toInt() - 1])
        }


        return LesData
    }

    fun returnContraintsMatrix(LesData: List<CellData>, dim: Int): Array<Array<String>> {
        var Matrix = Array(dim) { Array(dim) { "" } }
        //var LesData = generateMaze()
        var param = par
        for (data in LesData) {
            var x = (data.getx()).toInt()
            var y = (data.gety()).toInt()
            Matrix[(x/param).toInt() - 1][(y/param).toInt() - 1] = data.getwalls()
        }
        return Matrix
    }


    fun PathFinder(maze: Array<Array<String>>, x1: Int, y1: Int, x2: Int, y2: Int): List<String> {
        val movements = mutableListOf<String>()
        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue = ArrayDeque<Pair<Pair<Int, Int>, List<String>>>()
        queue.add(Pair(Pair(x1, y1), emptyList()))

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val x = current.first.first
            val y = current.first.second
            val path = current.second

            if (x == x2 && y == y2) {
                movements.addAll(path)
                break
            }

            if (visited.contains(Pair(x, y))) {
                continue
            }

            visited.add(Pair(x, y))

            if (y > 0 && maze[x][y][3] == '0') { // can go left
                queue.add(Pair(Pair(x, y - 1), path + "GoLeft"))
            }

            if (x > 0 && maze[x][y][0] == '0') { // can go up
                queue.add(Pair(Pair(x - 1, y), path + "GoUp"))
            }

            if (y < maze[0].size - 1 && maze[x][y][1] == '0') { // can go right
                queue.add(Pair(Pair(x, y + 1), path + "GoRight"))
            }

            if (x < maze.size - 1 && maze[x][y][2] == '0') { // can go down
                queue.add(Pair(Pair(x + 1, y), path + "GoDown"))
            }
        }


        for (movement in movements) {
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")
            println("Movement is: ${movement}")


        }

        return movements
    }




}