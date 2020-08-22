package com.mahipal.tictactoe.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahipal.core.data.datasource.OnViewUpdate
import com.mahipal.core.data.repository.TicTacToeRepository

/**
 * Created by Mahipal on 20/8/20
 */
class TicTacToeViewModel : ViewModel(), OnViewUpdate {

    val btnTopLeft = MutableLiveData<String>()
    val btnTopMiddle = MutableLiveData<String>()
    val btnTopRight = MutableLiveData<String>()
    val btnMiddleLeft = MutableLiveData<String>()
    val btnMiddleMiddle = MutableLiveData<String>()
    val btnMiddleRight = MutableLiveData<String>()
    val btnBottomLeft = MutableLiveData<String>()
    val btnBottomMiddle = MutableLiveData<String>()
    val btnBottomRight = MutableLiveData<String>()

    val labelToView = MutableLiveData<String>()
    val playerScore = MutableLiveData<String>()
    val player1Score = MutableLiveData<String>()

    private var ticTacToeRepository: TicTacToeRepository? = null

    init {
        ticTacToeRepository = TicTacToeRepository(this)
        resetBoard()
    }

    fun buttonClicked(row:Int,column:Int) {
        ticTacToeRepository?.buttonClicked(row,column)
    }

    private fun resetBoard() {
        ticTacToeRepository?.reset()
    }

    override fun setTextOnButton(row: Int, column: Int, ticTacToePlayer: String) {
        if (row == 0 && column==0) btnTopLeft.value = ticTacToePlayer
        else if (row == 1 && column==0) btnTopMiddle.value = ticTacToePlayer
        else if (row == 2 && column==0) btnTopRight.value = ticTacToePlayer
        else if (row == 0 && column==1) btnMiddleLeft.value = ticTacToePlayer
        else if (row == 1 && column==1) btnMiddleMiddle.value = ticTacToePlayer
        else if (row == 2 && column==1) btnMiddleRight.value = ticTacToePlayer
        else if (row == 0 && column==2) btnBottomLeft.value = ticTacToePlayer
        else if (row == 1 && column==2) btnBottomMiddle.value = ticTacToePlayer
        else if (row == 2 && column==2) btnBottomRight.value = ticTacToePlayer
    }

    override fun setLabelToView(text: String) {
        labelToView.value = text
    }

    override fun resetButtons() {
        val blank = ""

        btnTopLeft.value = blank
        btnTopMiddle.value = blank
        btnTopRight.value = blank
        btnMiddleLeft.value = blank
        btnMiddleMiddle.value = blank
        btnMiddleRight.value = blank
        btnBottomLeft.value = blank
        btnBottomMiddle.value = blank
        btnBottomRight.value = blank
    }

    override fun createOverlay(strMessage: String) {
        Log.d("TicTacToeViewModel","overalay ---> $strMessage")

        labelToView.value = strMessage
    }

    override fun setTxtPlayerScore(i: Int) {
        playerScore.value = i.toString()
    }

    override fun setTxtCPUScore(i: Int) {
        player1Score.value = i.toString()
    }
}