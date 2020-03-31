package com.example.cavesofkayvz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.cavesofkayvz.ui.game.GameFragment
import com.example.cavesofkayvz.ui.game.GameViewModel
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity() {

    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel.load(this)
        viewModel.initDungeon()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GameFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        viewModel.save(this)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        exitProcess(0)
    }
}
