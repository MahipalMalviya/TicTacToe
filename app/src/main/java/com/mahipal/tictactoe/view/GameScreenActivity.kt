package com.mahipal.tictactoe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mahipal.core.data.datasource.OnViewUpdate
import com.mahipal.core.data.repository.TicTacToeRepository
import com.mahipal.tictactoe.R
import com.mahipal.tictactoe.viewModel.TicTacToeViewModel
import kotlinx.android.synthetic.main.activity_main.*


class GameScreenActivity : AppCompatActivity(), OnViewUpdate {

    private var ticTacToeViewModel: TicTacToeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ticTacToeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TicTacToeViewModel::class.java)

        ticTacToeViewModel?.attachRepository(TicTacToeRepository(this))
        ticTacToeViewModel?.resetBoard()

        initViews()
    }

    private fun initViews() {
        btn_TL?.setOnClickListener { ticTacToeViewModel?.buttonClicked(0,0) }
        btn_TM?.setOnClickListener { ticTacToeViewModel?.buttonClicked(1,0) }
        btn_TR?.setOnClickListener { ticTacToeViewModel?.buttonClicked(2,0) }
        btn_ML?.setOnClickListener { ticTacToeViewModel?.buttonClicked(0,1) }
        btn_MM?.setOnClickListener { ticTacToeViewModel?.buttonClicked(1,1) }
        btn_MR?.setOnClickListener { ticTacToeViewModel?.buttonClicked(2,1) }
        btn_BL?.setOnClickListener { ticTacToeViewModel?.buttonClicked(0,2) }
        btn_BM?.setOnClickListener { ticTacToeViewModel?.buttonClicked(1,2) }
        btn_BR?.setOnClickListener { ticTacToeViewModel?.buttonClicked(2,2) }
    }

    override fun setTextOnButton(row: Int, column: Int, ticTacToePlayer: String) {
        if (row == 0 && column==0) btn_TL.text = ticTacToePlayer
        else if (row == 1 && column==0) btn_TM.text = ticTacToePlayer
        else if (row == 2 && column==0) btn_TR.text = ticTacToePlayer
        else if (row == 0 && column==1) btn_ML.text = ticTacToePlayer
        else if (row == 1 && column==1) btn_MM.text = ticTacToePlayer
        else if (row == 2 && column==1) btn_MR.text = ticTacToePlayer
        else if (row == 0 && column==2) btn_BL.text = ticTacToePlayer
        else if (row == 1 && column==2) btn_BM.text = ticTacToePlayer
        else if (row == 2 && column==2) btn_BR.text = ticTacToePlayer
    }

    override fun setLabelToView(text: String) {

    }

    override fun resetButtons() {
        val blank = ""

        btn_TL.text = blank
        btn_TM.text = blank
        btn_TR.text = blank
        btn_ML.text = blank
        btn_MM.text = blank
        btn_MR.text = blank
        btn_BL.text = blank
        btn_BM.text = blank
        btn_BR.text = blank
    }

    override fun createOverlay(strMessage: String) {
        Log.d("GameScreenActivity","overalay ---> $strMessage")

        Toast.makeText(this,strMessage,Toast.LENGTH_SHORT).show()
    }

    override fun setTxtPlayerScore(i: Int) {
        tv_player_score.text = i.toString()
    }

    override fun setTxtCPUScore(i: Int) {
        tv_cpu_score.text = i.toString()
    }
}
