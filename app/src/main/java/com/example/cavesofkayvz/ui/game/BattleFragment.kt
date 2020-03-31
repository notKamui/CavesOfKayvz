package com.example.cavesofkayvz.ui.game

import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.example.cavesofkayvz.R
import kotlinx.android.synthetic.main.battle_fragment.*
import kotlin.math.min

class BattleFragment : Fragment() {

    companion object {
        fun newInstance() = BattleFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.battle_fragment, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(GameViewModel::class.java) }!!

        val wachOut = view.findViewById<TextView>(R.id.wachout)
        wachOut.visibility = View.INVISIBLE

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                viewModel.battle.action()
                val wachOut = view.findViewById<TextView>(R.id.wachout)
                if(viewModel.battle.enemies.any {viewModel.battle.asb[it]!! > 800} && !viewModel.player.defenseUp)
                    wachOut.visibility = View.VISIBLE
                else
                    wachOut.visibility = View.INVISIBLE
                when (viewModel.battle.checkEnd()) {
                    1 -> {
                        val newFrag = GameFragment()
                        val trans = activity!!.supportFragmentManager.beginTransaction()
                        trans.replace(R.id.container, newFrag)
                        trans.addToBackStack(null)
                        trans.commit()
                    }
                    -1 -> {
                        val newFrag = GameFragment()
                        val trans = activity!!.supportFragmentManager.beginTransaction()
                        trans.replace(R.id.container, newFrag)
                        trans.addToBackStack(null)
                        trans.commit()
                    }
                }
                handler.postDelayed(this, 100)
            }
        }, 100)


        val enemy1Btn = view.findViewById<ImageView>(R.id.enemy1)
        val enemy2Btn = view.findViewById<ImageView>(R.id.enemy2)
        val blockButton = view.findViewById<Button>(R.id.blockBtn)

        viewModel.initBattle(view)


        enemy1Btn.setOnClickListener {
            if (viewModel.battle.asb[viewModel.player] == 1000) {
                viewModel.battle.asb[viewModel.player] = 0
                viewModel.player.attack(viewModel.battle.enemies[0])
                val mp = MediaPlayer.create(it.context, R.raw.hit)
                mp.start()
                if (viewModel.battle.enemies[0].hp <= 0) {
                    enemy1.visibility = View.INVISIBLE
                    it.setOnClickListener {  }
                }
            }
        }

        enemy2Btn.setOnClickListener {
            if (viewModel.battle.asb[viewModel.player] == 1000) {
                viewModel.battle.asb[viewModel.player] = 0
                viewModel.player.attack(viewModel.battle.enemies[1])
                val mp = MediaPlayer.create(it.context, R.raw.hit)
                mp.start()
                if (viewModel.battle.enemies[1].hp <= 0) {
                    enemy2.visibility = View.INVISIBLE
                    it.setOnClickListener {  }
                }
            }
        }

        blockButton.setOnClickListener {
            viewModel.player.defenseUp = true
            viewModel.battle.asb[viewModel.player] = 0
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(BattleViewModel::class.java)

    }
}
