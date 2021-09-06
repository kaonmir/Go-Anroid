package com.example.go.core

class BFS(private val board: List<Stone>) {
    private val answer = mutableListOf<Point>()
    private val visited = MutableList(19*19) { false }
    private var t: Stone = board[0]

    private fun bfs(p: Point) {
        if (visited[p.cur] || board[p.cur].isEnemy(t)) return;
        visited[p.cur] = true

        answer.add(p)
        p.toFourWays().filter { !visited[it.cur] }.forEach { bfs(it) }
    }

    fun getAlliances(p: Point): List<Point> {
        answer.clear()
        visited.forEachIndexed { idx, _ -> visited[idx] = false }
        t = board[p.cur]
        bfs(p)
        return answer
    }
}