package com.example.cavesofkayvz.ui.newgame

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cavesofkayvz.GameActivity

import com.example.cavesofkayvz.R
import kotlinx.android.synthetic.main.new_game2_fragment.*
import kotlin.system.exitProcess

class NewGameFragment2 : Fragment() {

    companion object {
        fun newInstance() = NewGameFragment2()
    }

    private lateinit var viewModel: NewGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.new_game2_fragment, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(NewGameViewModel::class.java) }!!

        val minHP = view.findViewById<Button>(R.id.minHP)
        minHP.setOnClickListener {
            if (viewModel.allocPts < 5 && viewModel.maxHP > 1) {
                viewModel.allocPts++
                viewModel.maxHP--
                updateView()
            }
        }

        val plusHP = view.findViewById<Button>(R.id.plusHP)
        plusHP.setOnClickListener {
            if (viewModel.allocPts > 0) {
                viewModel.allocPts--
                viewModel.maxHP++
                updateView()
            }
        }

        val minSTR = view.findViewById<Button>(R.id.minSTR)
        minSTR.setOnClickListener {
            if (viewModel.allocPts < 5 && viewModel.str > 1) {
                viewModel.allocPts++
                viewModel.str--
                updateView()
            }
        }

        val plusSTR = view.findViewById<Button>(R.id.plusSTR)
        plusSTR.setOnClickListener {
            if (viewModel.allocPts > 0) {
                viewModel.allocPts--
                viewModel.str++
                updateView()
            }
        }

        val minDEF = view.findViewById<Button>(R.id.minDEF)
        minDEF.setOnClickListener {
            if (viewModel.allocPts < 5 && viewModel.def > 1) {
                viewModel.allocPts++
                viewModel.def--
                updateView()
            }
        }

        val plusDEF = view.findViewById<Button>(R.id.plusDEF)
        plusDEF.setOnClickListener {
            if (viewModel.allocPts > 0) {
                viewModel.allocPts--
                viewModel.def++
                updateView()
            }
        }

        val minDEX = view.findViewById<Button>(R.id.minDEX)
        minDEX.setOnClickListener {
            if (viewModel.allocPts < 5 && viewModel.dex > 1) {
                viewModel.allocPts++
                viewModel.dex--
                updateView()
            }
        }

        val plusDEX = view.findViewById<Button>(R.id.plusDEX)
        plusDEX.setOnClickListener {
            if (viewModel.allocPts > 0) {
                viewModel.allocPts--
                viewModel.dex++
                updateView()
            }
        }

        val start = view.findViewById<Button>(R.id.startBtn)
        start.setOnClickListener {
            viewModel.save(it.context)
            val intent = Intent(it.context, GameActivity::class.java)
            startActivity(intent)
            activity!!.finish()
            exitProcess(0)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(NewGameViewModel::class.java)
        updateView()
    }

    private fun updateView() {
        pleftVal.text = viewModel.allocPts.toString()
        hpFp.text = viewModel.maxHP.toString()
        strFp.text = viewModel.str.toString()
        defFp.text = viewModel.def.toString()
        dexFp.text = viewModel.dex.toString()

        hpFe.text = (viewModel.maxHP * 100).toString()
        strFe.text = (viewModel.str * 10).toString()
        defFe.text = (viewModel.def * 10).toString()
        dexFe.text = (viewModel.dex * 10).toString()
    }

}
