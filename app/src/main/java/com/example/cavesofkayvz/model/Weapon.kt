package com.example.cavesofkayvz.model

abstract class Weapon(name: String, val pow: Int, val range: List<Int>): Item(name) // range 1 2 3 4

class Dummy : Weapon("", 0, listOf())

class Dagger(name: String, pow: Int) : Weapon(name, pow, listOf(1, 2))

class SmallGun(name: String, pow: Int) : Weapon(name, pow, listOf(3, 4))
