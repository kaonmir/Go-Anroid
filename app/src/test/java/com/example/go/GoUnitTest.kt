package com.example.go

import com.example.go.core.Go
import com.example.go.core.Point
import com.example.go.core.Stone
import org.junit.Test
import org.junit.Assert.*
import org.junit.BeforeClass

class GoUnitTest {
    @Test
    fun checkGoConstructor() {
        val go = Go(arrayOf(30, 100, 10))
        assertEquals(go.turn, Stone.StoneType.WHITE)
        assertEquals(go.board[30].type, Stone.StoneType.BLACK)
        assertEquals(go.board[100].type, Stone.StoneType.WHITE)
        assertEquals(go.board[10].type, Stone.StoneType.BLACK)
    }

    @Test
    fun checkMoveFunction() {
        val go = Go()
        go.move(30)
        go.move(100)
        go.move(10)
        assertEquals(go.turn, Stone.StoneType.WHITE)
        assertEquals(go.board[30].type, Stone.StoneType.BLACK)
        assertEquals(go.board[100].type, Stone.StoneType.WHITE)
        assertEquals(go.board[10].type, Stone.StoneType.BLACK)
    }
}