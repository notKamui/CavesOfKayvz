package com.example.cavesofkayvz.model

import kotlin.random.Random

class Treasure : Room(null) {
    private val loot = listOf<Item>(
        Dagger("com.example.cavesofkayvz.model.Main Gauche", 3),
        Dagger("Kitchen knife", 1),
        PlateArmor("Wooden armor", 1),
        PlateArmor("Bronze com.example.cavesofkayvz.model.Armor", 2),
        HealthPotion("Minor Health Potion", 5),
        HealthPotion("Medium Health Potion", 15)
    )

    override fun action() {
        println("You found a treasure ! You get : ")
        for (i in 0..Random.nextInt(1, 2)) {
            var item = loot.random()
            println("- ${item.name}")
            player?.inventory?.items?.add(item)
        }
        visited = true
    }

}