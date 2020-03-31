package com.example.cavesofkayvz.model

import android.media.MediaPlayer
import android.view.View
import com.example.cavesofkayvz.R
import kotlinx.android.synthetic.main.battle_fragment.view.*
import kotlin.math.min

class Battle(var enemies: MutableList<Enemy>) : Room(null) {

    val asb = mutableMapOf<Soma, Int>()
    lateinit var view: View

    fun initBattle(view: View) {
        this.view = view
        player?.let { asb.put(it, 0) }
        enemies.forEach { asb[it] = 0 }

        view.hpGauge.progress = 100
    }

    override fun action() {
        asb.keys.forEach{
            if (it.hp > 0) {
                asb[it] = min(asb[it]?.plus(it.dex)!!, 1000)
            }
        }
        enemies.forEach {
            if (asb[it] == 1000 && it.hp > 0) {
                val mp = MediaPlayer.create(view.context, R.raw.hurt)
                mp.start()
                it.attack(player!!)
                asb[it] = 0
            }
        }
        updateBar()
    }

    private fun updateBar() {
        view.hpGauge.progress = ((player!!.hp.toDouble()/player!!.maxHP)*100).toInt()
        var p: Soma? = null
        asb.keys.forEach { if (it is Player) p = it }
        view.asbGauge.progress = ((asb[p]!!.toDouble()/1000)*100).toInt()
    }

    fun checkEnd(): Int {
        return when {
            player!!.hp <= 0 -> -1
            enemies.none { it.hp > 0 } -> 1
            else -> 0
        }
    }

}