package com.example.cavesofkayvz.ui.game

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.cavesofkayvz.model.*
import kotlinx.android.synthetic.main.battle_fragment.view.*

class GameViewModel : ViewModel() {
    companion object {
        val enemiesPool = listOf(Bat("", 5, 300, 20, 40), Skeleton("", 5, 500, 30, 30))
    }

    lateinit var player: Player
    lateinit var dungeon: Dungeon
    lateinit var battle: Battle

    fun load(context: Context) {
        player = Utility.load(context)
    }

    fun save(context: Context) {
        Utility.save(player, context)
    }

    fun initDungeon() {
        dungeon = Dungeon()
        val floor = Floor()
        floor.createFloor(player)
        dungeon.floors.add(floor)
    }

    fun initBattle(view: View) {
        battle = Battle(mutableListOf(enemiesPool.random(), enemiesPool.random()))
        battle.player = player
        battle.initBattle(view)
        battle.action()
    }
}
