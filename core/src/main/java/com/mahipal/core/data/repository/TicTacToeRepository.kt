package com.mahipal.core.data.repository

import com.mahipal.core.data.datasource.OnViewUpdate
import com.mahipal.core.domain.TicTacToe

/**
 * Created by Mahipal on 20/8/20
 */
class TicTacToeRepository(onViewUpdate: OnViewUpdate) {

    private val ticTacToe = TicTacToe()

    init {
        ticTacToe.attachCallback(onViewUpdate)
    }

    fun reset() {
        ticTacToe.resetBoard()
    }

    fun buttonClicked(row:Int,column:Int) {
        ticTacToe.buttonClicked(row,column)
    }

}