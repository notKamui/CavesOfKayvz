package com.example.cavesofkayvz.ui.newgame

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.cavesofkayvz.model.Dagger
import com.example.cavesofkayvz.model.PlateArmor
import com.example.cavesofkayvz.model.Player
import com.example.cavesofkayvz.model.Utility

class NewGameViewModel : ViewModel() {
    var name: String = ""
    var maxHP: Int = 6
    var def: Int = 5
    var dex: Int = 2
    var str: Int = 3

    var allocPts: Int = 5

    fun selectClass(type: Int) {
        when(type) {
            1 -> setStats(6, 5, 2, 3)
            2 -> setStats(4, 4, 4, 4)
            3 -> setStats(3, 2, 6, 5)
            4 -> setStats(3, 6, 2, 5)
        }
    }

    private fun setStats(maxHP: Int, def: Int, dex: Int, str: Int) {
        this.maxHP = maxHP
        this.def = def
        this.dex = dex
        this.str = str
    }

    fun save(context: Context) {
        Utility.save(Player(name, maxHP+str+def+dex, maxHP*100, def*10, dex*10, str*10, Dagger("SmallDagger", 2), PlateArmor("SimplePlate", 2)), context)
    }

}
