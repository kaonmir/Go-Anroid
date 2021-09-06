package com.example.go.core

class Stone(var type: StoneType? = null) {
    enum class StoneType {
        BLACK {
            override fun enemy() = WHITE
        },
        WHITE {
            override fun enemy() = BLACK
        };

        abstract fun enemy(): StoneType
    }

    fun isEmpty() = type == null

    fun isEnemy(t: Stone): Boolean {
        if (t.isEmpty() or isEmpty()) return false
        return t.type != type
    }

    fun clear() {
        type = null
    }

    fun enemy(): StoneType {
        return when (type) {
            StoneType.BLACK -> StoneType.WHITE
            StoneType.WHITE -> StoneType.BLACK
            else -> TODO("Error Handling")
        }
    }
}