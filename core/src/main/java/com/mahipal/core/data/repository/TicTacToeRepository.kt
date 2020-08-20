package com.mahipal.core.data.repository

import com.mahipal.core.data.datasource.OnViewUpdate
import com.mahipal.core.data.datasource.TicTacToeDataSource
import com.mahipal.core.domain.TicTacToe

/**
 * Created by Mahipal on 20/8/20
 */
class TicTacToeRepository(private val onViewUpdate: OnViewUpdate) : TicTacToeDataSource {

    private val ticTacToe = TicTacToe()

    override fun onButtonClick(row: Int, column: Int) {
        buttonClicked(row,column)
    }

    override fun resetBoard() {
        reset()
    }

    private fun reset() {
        for (x in 0..2) {
            for (y in 0..2) {
                ticTacToe.board[x][y] = 0
            }
        }
        onViewUpdate.resetButtons()
        onViewUpdate.setLabelToView("Player ${ticTacToe.player}'s move")
    }

    private fun buttonClicked(row:Int,column:Int) : Int {
        if (ticTacToe.board[row][column] != 0) return 0

        if (ticTacToe.board[row][column] == 0 && ticTacToe.player == 1) {
            setBoard(row,column)
            onViewUpdate.setTextOnButton(row,column,ticTacToe.p1)
        }else if(ticTacToe.board[row][column] == 0 && ticTacToe.player == 2){
            setBoard(row,column)
            onViewUpdate.setTextOnButton(row,column,ticTacToe.p2)
        }

        checkBoard()
        switchPlayer()

        return 1
    }

    private fun setBoard(x: Int, y: Int) {
        ticTacToe.board[x][y] = ticTacToe.player
    }

    private fun switchPlayer() {
        ticTacToe.player = ticTacToe.player xor 3

        onViewUpdate.setLabelToView("Player ${ticTacToe.player}'s turn")
    }

    private fun checkBoard() {
        if (checkBoardDirections(ticTacToe.board)) {
            showOverlay("Player ${ticTacToe.player} has won!")
            setScore(1)
            resetBoard()
        } else if (checkFullBoard()) {
            showOverlay("Tie Game")
            setScore(2)
            resetBoard()
        }

    }

    private fun showOverlay(strMessage: String) {
        onViewUpdate.createOverlay(strMessage)
    }

    private fun checkBoardDirections(testBoard: Array<IntArray>): Boolean {

        //Check columns
        for (x in 0..2) {
            if (testBoard[x][0] != 0 && testBoard[x][0] == testBoard[x][1] && testBoard[x][1] == testBoard[x][2])
                return true
        }

        //Check rows
        for (y in 0..2) {
            if (testBoard[0][y] != 0 && testBoard[0][y] == testBoard[1][y] && testBoard[1][y] == testBoard[2][y])
                return true
        }

        //Diagonal right
        if (testBoard[0][0] != 0 && testBoard[0][0] == testBoard[1][1] && testBoard[1][1] == testBoard[2][2]) {
            return true
        }

        //Diagonal Left
        if (testBoard[0][2] != 0 && testBoard[0][2] == testBoard[1][1] && testBoard[1][1] == testBoard[2][0]) {
            return true
        }

        return false
    }

    private fun setScore(choice: Int) {
        if (choice == 1) { //A single player won
            ticTacToe.score[ticTacToe.player - 1] += 1
        } else {  //players tied
            //            score[0]+=1;
            //            score[1]+=1;
        }

        onViewUpdate.setTxtPlayerScore(ticTacToe.score[0])
        onViewUpdate.setTxtCPUScore(ticTacToe.score[1])
    }

    private fun checkFullBoard(): Boolean {
        for (x in 0..2) {
            for (y in 0..2) {
                if (ticTacToe.board[x][y] == 0) return false
            }
        }

        return true
    }
}