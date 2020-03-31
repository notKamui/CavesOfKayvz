package com.example.cavesofkayvz.model

abstract class Armor(name: String, var def: Int) : Item(name)

class PlateArmor(name: String, def: Int) : Armor(name, def)

class DummyArmor : Armor("", 0)