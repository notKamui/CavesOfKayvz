package com.example.cavesofkayvz.model

import kotlin.math.max

var maxID = 0


abstract class Soma(var name: String, var lvl: Int, var maxHP: Int, var def: Int, var dex: Int) {
    val id = maxID++
    var hp = maxHP
    var defenseUp = false
    open val effectiveDef: Int
        get() = def

    abstract fun attack(other: Soma)

    fun endure(hitPts: Int) {
        if (defenseUp)
            defenseUp = false
        hp = max(0, hp - (hitPts + effectiveDef * (if (defenseUp) 2 else 1)))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Soma

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}