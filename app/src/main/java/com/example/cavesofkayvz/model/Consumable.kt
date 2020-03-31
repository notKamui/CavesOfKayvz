package com.example.cavesofkayvz.model

import kotlin.math.min

abstract class Consumable(name: String) : Item(name) {
    abstract fun use(target: Soma)
}

class HealthPotion(name: String, var heal: Int) : Consumable(name) {
    override fun use(target: Soma) {
        target.hp = min(target.maxHP, target.hp + heal)
    }

}