package com.example.cavesofkayvz.model

class Inventory(var player: Player) {
    var items: MutableList<Item> = mutableListOf()

    fun use(slot: Int) {
        when (val item = items[slot]) {
            is Consumable -> item.use(player)
            is Weapon -> player.weapon = item
            is Armor -> player.armor = item
        }
    }


}