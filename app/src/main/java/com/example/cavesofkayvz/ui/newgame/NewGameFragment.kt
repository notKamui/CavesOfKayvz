package com.example.cavesofkayvz.ui.newgame

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.cavesofkayvz.NewGameActivity
import com.example.cavesofkayvz.R
import kotlinx.android.synthetic.main.new_game_fragment.*

class NewGameFragment : Fragment() {

    companion object {
        fun newInstance() = NewGameFragment()
    }

    private lateinit var viewModel: NewGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.new_game_fragment, container, false)
        viewModel = activity?.let { ViewModelProviders.of(it).get(NewGameViewModel::class.java) }!!
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(NewGameViewModel::class.java)
        selectClass(1)

        charNameField.afterTextChanged { viewModel.name = charNameField.text.toString() }

        warriorBtn.setOnClickListener { selectClass(1) }
        commonerBtn.setOnClickListener { selectClass(2) }
        rogueBtn.setOnClickListener { selectClass(3) }
        mageBtn.setOnClickListener { selectClass(4) }
    }

    private fun selectClass(type: Int) {
        viewModel.selectClass(type)
        hpF.text = (viewModel.maxHP * 100).toString()
        strF.text = (viewModel.str * 10).toString()
        defF.text = (viewModel.def * 10).toString()
        dexF.text = (viewModel.dex * 10).toString()
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

}
