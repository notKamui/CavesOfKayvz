package com.example.cavesofkayvz.model

class Player(
    name: String,
    lvl: Int,
    maxHP: Int,
    def: Int,
    dex: Int,
    var str: Int,
    var weapon: Weapon,
    var armor: Armor
) :
    Soma(name, lvl, maxHP, def, dex) {
    lateinit var currentRoom: Room
    var inventory = Inventory(this)
    private val atk: Int
        get() = str * (weapon.pow / 2 + 1)
    override val effectiveDef: Int
        get() = def + armor.def

    override fun attack(other: Soma) {
        other.endure(atk)
    }
}