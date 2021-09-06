package com.example.go.core

import android.util.Log


class Go(private val logs: Array<Int>) {
    val board = (0 until 19 * 19).map { Stone() }
    val counts = mutableMapOf(Stone.StoneType.BLACK to 0, Stone.StoneType.WHITE to 0)
    var turn: Stone.StoneType = Stone.StoneType.BLACK

    private var bfs = BFS(board)

    init {
        logs.forEach { cur -> move(Point(cur)) }
    }

    constructor() : this(arrayOf())

    fun move(cur: Int) = move(Point(cur))
    fun move(p: Point) {
        if (!board[p.cur].isEmpty()) throw ErrorEntity.WrongPoint
        board[p.cur].type = turn

        val count = canKill(p)

        if (count == 0 && callBeKilled(p)) {
            board[p.cur].type = null
            throw ErrorEntity.DeathCorner
        } else {
            counts[turn] = counts[turn]!! + count
            turn = turn.enemy()
        }
    }

    private fun callBeKilled(p: Point): Boolean {
        if (board[p.cur].isEmpty()) return false
        val alliances = bfs.getAlliances(p)
        return alliances.all { alliance -> alliance.toFourWays().all { !board[it.cur].isEmpty() } }
    }

    private fun canKill(cur: Point): Int {
        return cur.toFourWays()
            .filter { dst -> board[cur.cur].isEnemy(board[dst.cur]) }
            .filter { callBeKilled(it) }
            .map { dst ->
                val alliances = bfs.getAlliances(dst)
                alliances.forEach { board[it.cur].clear() }
                return@map alliances.size
            }.fold(0) { acc, num -> acc + num }
    }
}