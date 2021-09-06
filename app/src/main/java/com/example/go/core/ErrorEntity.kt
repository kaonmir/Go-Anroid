package com.example.go.core

sealed class ErrorEntity(message: String): Throwable(message) {
    object LogError: ErrorEntity("Log is improper to make board")
    object WrongPoint: ErrorEntity("You can never put a stone on that point")
    object DeathCorner: ErrorEntity("Don't let yourself die")
}