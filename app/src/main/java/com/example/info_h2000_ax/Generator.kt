package com.example.info_h2000_ax


class Generator (val maze: Maze, val dim: Int, val par: Float){

    var dimension = dim

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

        //val gen = Generator(dimension, param)
        val LesData = generateBoxes(dimension, param)
        //val maze = Maze(dimension, dimension, param)
        val output = maze.returnWalls()

        for (data in LesData) {
            var x = data.getx()
            var y = data.gety()
            data.modifwalls(output[(y/param).toInt() - 1][(x/param).toInt() - 1])
        }


        return LesData
    }

    fun returnContraintsMatrix(maze: Maze, dim: Int): Array<Array<String>> {
        var Matrix = Array(dim) { Array(dim) { "" } }
        var LesData = generateMaze()
        var param = par
        for (data in LesData) {
            var x = (data.getx()).toInt()
            var y = (data.gety()).toInt()
            Matrix[(x/param).toInt() - 1][(y/param).toInt() - 1] = data.getwalls()
        }
        return Matrix
    }
}