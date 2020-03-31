package com.example.cavesofkayvz.model

abstract class Enemy(name: String, lvl: Int, maxHP: Int, def: Int, dex: Int) : Soma(name, lvl, maxHP, def, dex)

class Bat(name: String, lvl: Int, maxHP: Int, def: Int, dex: Int) : Enemy(name, lvl, maxHP, def, dex) {
    override fun attack(other: Soma) {
        val hitPts = lvl * 5 + (if ((0..2).random() == 0) lvl * 5 else 0)
        other.endure(hitPts)
    }
}

class Skeleton(name: String, lvl: Int, maxHP: Int, def: Int, dex: Int) : Enemy(name, lvl, maxHP, def, dex) {
    override fun attack(other: Soma) {
        other.endure(lvl * 10)
    }
}