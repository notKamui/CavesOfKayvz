package com.example.cavesofkayvz.model

class Safe(player: Player) : Room(player) {
    override fun action() {
        println("You're in a safe zone !")
        visited = true
        player!!.currentRoom = this
    }

}