package com.example.cavesofkayvz.model

abstract class Room(player: Player?) {
    var neighbors: MutableList<Room> = mutableListOf()
    var visited = false
    var player: Player? = player
    abstract fun action()
}