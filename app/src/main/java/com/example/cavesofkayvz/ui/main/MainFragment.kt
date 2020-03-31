package com.example.cavesofkayvz.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cavesofkayvz.GameActivity
import com.example.cavesofkayvz.HTPActivity
import com.example.cavesofkayvz.NewGameActivity
import com.example.cavesofkayvz.R
import kotlin.system.exitProcess

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        val ngButton = view.findViewById<Button>(R.id.newGameButton)
        ngButton.setOnClickListener {
            val intent = Intent(it.context, NewGameActivity::class.java)
            startActivity(intent)
            activity!!.finish()
            exitProcess(0)
        }

        val continueBtn = view.findViewById<Button>(R.id.continueButton)
        continueBtn.setOnClickListener {
            val intent = Intent(it.context, GameActivity::class.java)
            startActivity(intent)
            activity!!.finish()
            exitProcess(0)
        }

        val htpButton = view.findViewById<Button>(R.id.howToPlayButton)
        htpButton.setOnClickListener {
            val intent = Intent(it.context, HTPActivity::class.java)
            startActivity(intent)
            activity!!.finish()
            exitProcess(0)
        }
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
