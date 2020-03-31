package com.example.cavesofkayvz.model

class Stairs : Room(null) {
    override fun action() {
        //get to next floor
        visited = true
    }

}