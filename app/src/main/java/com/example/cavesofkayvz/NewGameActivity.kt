package com.example.cavesofkayvz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.example.cavesofkayvz.ui.newgame.NewGameFragment
import com.example.cavesofkayvz.ui.newgame.NewGameFragment2
import com.example.cavesofkayvz.ui.newgame.NewGameViewModel
import kotlinx.android.synthetic.main.new_game_activity.*
import kotlinx.android.synthetic.main.new_game_fragment.*
import kotlin.system.exitProcess

class NewGameActivity : AppCompatActivity() {

    lateinit var viewModel: NewGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(NewGameViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewGameFragment.newInstance())
                .commitNow()
        }

        val okBtn = findViewById<Button>(R.id.ngOkBtn)
        okBtn.setOnClickListener {
            if (charNameField.text.isNotEmpty()) {
                ngOkBtn.visibility = View.INVISIBLE
                val newFrag = NewGameFragment2()
                val trans = supportFragmentManager.beginTransaction()

                trans.replace(R.id.container, newFrag)
                trans.addToBackStack(null)

                trans.commit()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        exitProcess(0)
    }
}
