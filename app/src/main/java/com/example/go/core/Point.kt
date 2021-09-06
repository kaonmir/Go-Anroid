package com.example.go.core

class Point(val cur: Int, private val BoardSize: Int = 19) {
    var x: Int private set
    var y: Int private set

    init {
        x = cur / BoardSize
        y = cur % BoardSize
    }

    constructor(x: Int, y: Int, BoardSize: Int = 19) : this(x * BoardSize + y, BoardSize) {
        this.x = x
        this.y = y
    }

    private fun isOutBound() = x < 0 || BoardSize <= x || y < 0 || BoardSize <= y

    fun toFourWays(): List<Point> {
        val ms = arrayOf(
            arrayOf(0, -1), arrayOf(0, 1),
            arrayOf(-1, 0), arrayOf(1, 0),
        )

        return ms.map { m -> Point(x = m[0] + x, y = m[1] + y) }.filter { !it.isOutBound() }
    }
}