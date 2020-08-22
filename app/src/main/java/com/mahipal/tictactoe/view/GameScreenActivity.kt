package com.mahipal.tictactoe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mahipal.tictactoe.R
import com.mahipal.tictactoe.databinding.ActivityMainBinding
import com.mahipal.tictactoe.viewModel.TicTacToeViewModel


class GameScreenActivity : AppCompatActivity() {

    private var ticTacToeViewModel: TicTacToeViewModel? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        ticTacToeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TicTacToeViewModel::class.java)

        binding?.ticTacToeViewModel = ticTacToeViewModel
        binding?.lifecycleOwner = this

        observeValues()
    }

    private fun observeValues() {
        ticTacToeViewModel?.labelToView?.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }

}
