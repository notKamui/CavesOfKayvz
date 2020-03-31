package com.example.cavesofkayvz.model

import kotlin.math.min

fun main() {
    Main.main()
}

class Main {
    companion object {
        @JvmStatic
        fun main() {
            val player = Player("jak", 13, 600, 6, 5, 4, Dagger("Simple", 2), PlateArmor("Simple", 2))

            val enemies = mutableMapOf<Int, Enemy>()
            enemies[1] = Skeleton("noak", 2, 100, 10, 20)
            enemies[3] = Bat("boink", 2, 100, 10, 30)

            var asb = mutableMapOf<Soma, Int?>()
            asb[player] = 0
            enemies.values.forEach { asb[it] = 0 }



            battleLoop@ while (true) {
                for (soma in asb.keys) {
                    asb[soma] = asb[soma]?.plus(soma.dex)?.let { min(100, it) }
                    if (asb[soma]!! >= 100) {
                        asb[soma] = 0
                        when (soma) {
                            is Player -> {
                                println("Quelle position attaquer ?")
                                enemies.forEach { println("${if (player.weapon.range.contains(it.key)) " " else "X"} ${it.key} ${it.value.name}") }
                                var e: Int
                                do {
                                    e = try {
                                        readLine()!!.toInt()
                                    } catch (ex: NumberFormatException) {
                                        0
                                    }
                                } while (!player.weapon.range.contains(e))

                                println("You attack ${enemies[e]?.name} which has ${enemies[e]?.hp}hp left")
                                enemies[e]?.let { soma.attack(it) }
                                println("It now has ${enemies[e]?.hp}hp\n")
                            }
                            is Enemy -> {
                                println("You have ${player.hp}hp left and are attacked by ${soma.name}")
                                soma.attack(player)
                                println("You now have ${player.hp}hp\n")
                                if (player.hp <= 0) break@battleLoop
                            }
                        }
                    }
                    if (asb.size <= 1) break@battleLoop
                }
                asb = asb.filter { it.key.hp > 0 }.toMutableMap()
            }
            if (player.hp <= 0)
                println("You lost")
            else
                println("You won")
        }
    }
}