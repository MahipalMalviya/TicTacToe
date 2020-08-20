package com.mahipal.core.data.datasource

/**
 * Created by Mahipal on 20/8/20
 */
interface TicTacToeDataSource {
    fun onButtonClick(row:Int, column:Int)
    fun resetBoard()
}