package com.example.cavesofkayvz.model

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

val file = File("resources/save.txt")

abstract class Utility {
    companion object {
        fun save(player: Player, context: Context) {
            val save = """
                ${player.name} ${player.lvl} ${player.hp} ${player.maxHP} ${player.def} ${player.dex} ${player.str}
                ${
            when (player.weapon) {
                is Dagger -> "Dagger"
                is SmallGun -> "SmallGun"
                else -> ""
            }
            } ${player.weapon.name} ${player.weapon.pow}
                ${
            when (player.armor) {
                is PlateArmor -> "PlateArmor"
                else -> ""
            }
            } ${player.armor.name} ${player.armor.def}
            """.trimIndent()

            val path = context.filesDir
            val letDir = File(path, "LET")
            letDir.mkdirs()
            val file = File(letDir, "save.txt")
            FileOutputStream(file).use { it.write(save.toByteArray()) }
        }

        fun load(context: Context): Player {
            val path = context.filesDir
            val letDir = File(path, "LET")
            letDir.mkdirs()
            val file = File(letDir, "save.txt")
            val save = FileInputStream(file).bufferedReader().use { it.readText() }.split("\n")

            val pInfos = save[0].split(" ")
            val wInfos = save[1].split(" ")
            val aInfos = save[2].split(" ")

            val player = Player(
                pInfos[0],
                pInfos[1].toInt(),
                pInfos[3].toInt(),
                pInfos[4].toInt(),
                pInfos[5].toInt(),
                pInfos[6].toInt(),
                when (wInfos[0]) {
                    "Dagger" -> Dagger(wInfos[1], wInfos[2].toInt())
                    "SmallGun" -> SmallGun(wInfos[1], wInfos[2].toInt())
                    else -> Dummy()
                },
                when (aInfos[0]) {
                    "PlateArmor" -> PlateArmor(aInfos[1], aInfos[2].toInt())
                    else -> DummyArmor()
                }
            )
            player.hp = pInfos[2].toInt()

            return player
        }
    }
}