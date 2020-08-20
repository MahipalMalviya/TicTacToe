package com.mahipal.core.data.datasource

/**
 * Created by Mahipal on 20/8/20
 */
interface OnViewUpdate {
    fun setTextOnButton(row:Int,column:Int,ticTacToePlayer:String)
    fun resetButtons()
    fun createOverlay(strMessage: String)
    fun setTxtPlayerScore(i: Int)
    fun setTxtCPUScore(i: Int)
}