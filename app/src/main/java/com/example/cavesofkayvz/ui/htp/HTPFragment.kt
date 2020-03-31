package com.example.cavesofkayvz.ui.htp

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cavesofkayvz.HTPActivity
import com.example.cavesofkayvz.MainActivity
import com.example.cavesofkayvz.R
import kotlin.system.exitProcess

class HTPFragment : Fragment() {

    companion object {
        fun newInstance() = HTPFragment()
    }

    private lateinit var viewModel: HTPViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.htp_fragment, container, false)
        val htpButton = view.findViewById<Button>(R.id.mainMenuButton)
        htpButton.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            startActivity(intent)
            activity!!.finish()
            exitProcess(0)
        }
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HTPViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
