package com.mahipal.tictactoe.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahipal.core.data.repository.TicTacToeRepository
import com.mahipal.core.domain.TicTacToe

/**
 * Created by Mahipal on 20/8/20
 */
class TicTacToeViewModel : ViewModel() {

    private var ticTacToeRepository: TicTacToeRepository? = null

    fun attachRepository(ticTacToeRepository: TicTacToeRepository) {
        this.ticTacToeRepository = ticTacToeRepository
    }

    fun buttonClicked(row:Int,column:Int) {
        ticTacToeRepository?.onButtonClick(row,column)
    }

    fun resetBoard() {
        ticTacToeRepository?.resetBoard()
    }
}