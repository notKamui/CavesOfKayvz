package com.example.cavesofkayvz.ui.game

import android.graphics.Rect
import android.graphics.drawable.shapes.Shape
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.core.view.children
import com.example.cavesofkayvz.R
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.game_fragment, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(GameViewModel::class.java) }!!

        val battleBtn = view.findViewById<Button>(R.id.battleBtn)
        battleBtn.setOnClickListener {
            val newFrag = BattleFragment()
            val trans = activity!!.supportFragmentManager.beginTransaction()

            trans.replace(R.id.container, newFrag)
            trans.addToBackStack(null)

            trans.commit()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        updateInfo()
    }

    private fun updateInfo() {
        nameBox.text = viewModel.player.name
        hpBoxF.text = viewModel.player.hp.toString()
        hpMaxBoxF.text = viewModel.player.maxHP.toString()
    }

    private fun updateMap() {
    }

}
