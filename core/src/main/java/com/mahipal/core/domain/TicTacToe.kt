package com.mahipal.core.domain

import com.mahipal.core.data.datasource.OnViewUpdate

/**
 * Created by Mahipal on 20/8/20
 */

/**
 * This Class represents as TicTacToe Game which is used for player's move, player's actions,
 * player's Symbols.
 */

class TicTacToe {

    /**
     *  TicTacToe player's action on 3X3 board.
     */
    private var board = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))

    /**
     * TicTacToe Player's turn
     */
    private var player = 1

    /**
     * TicTacToe Player's Symbol as X
     */
    private val p1 = "X"

    /**
     * TicTacToe Player's Symbol as O
     */
    private val p2 = "O"

    /**
     * TicTacToe Player's Score
     */
    private var score = intArrayOf(0, 0)

    private var onViewUpdate:OnViewUpdate? = null

    /**
     * Callback for UI Update
     * @param onViewUpdate This is reference of Interface for UI Update.
     */
    fun attachCallback(onViewUpdate: OnViewUpdate) {
        this.onViewUpdate = onViewUpdate
    }


    /**
     * A Player click on 3X3 board then save the position of player.
     * @param row A player click on row of 3X3 board.
     * @param column A player click on column of 3X3 board.
     */
    fun buttonClicked(row:Int,column:Int) {
        if (board[row][column] != 0) return

        if (board[row][column] == 0 && player == 1) {
            setBoard(row,column)
            onViewUpdate?.setTextOnButton(row,column,p1)
        }else if(board[row][column] == 0 && player == 2){
            setBoard(row,column)
            onViewUpdate?.setTextOnButton(row,column,p2)
        }

        checkBoard()
        switchPlayer()
    }


    /**
     *  A player's move save into two dimensional array.
     */
    private fun setBoard(x: Int, y: Int) {
        board[x][y] = player
    }

    /**
     * Reset Player's data and update to UI.
     */
    fun resetBoard() {
        for (x in 0..2) {
            for (y in 0..2) {
                board[x][y] = 0
            }
        }
        onViewUpdate?.resetButtons()
        onViewUpdate?.setLabelToView("Player ${player}'s move")
    }

    /**
     * switching another player after player turn.
     */
    private fun switchPlayer() {
        player = player xor 3

        onViewUpdate?.setLabelToView("Player ${player}'s turn")
    }

    /**
     * Check user win or tie the game.
     */
    private fun checkBoard() {
        if (checkBoardDirections(board)) {
            showOverlay("Player $player has won!")
            setScore(1)
            resetBoard()
        } else if (checkFullBoard()) {
            showOverlay("Tie Game")
            setScore(2)
            resetBoard()
        }

    }

    /**
     * Show message to UI.
     */
    private fun showOverlay(strMessage: String) {
        onViewUpdate?.createOverlay(strMessage)
    }

    /**
     * This function check a player's win possibility in different directions.Such as check columns,
     * check rows, check diagonal right, diagonal left.
     * @param testBoard is player's stored actions in array of IntegerArray.
     */
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

    /**
     * Set player score after player win and Update to UI.
     * @param choice is score if choice is 1 that means player has won. So, score will be increment.
     * and update the UI
     */
    private fun setScore(choice: Int) {
        if (choice == 1) { //A single player won
            score[player - 1] += 1
        }
        onViewUpdate?.setTxtPlayerScore(score[0])
        onViewUpdate?.setTxtCPUScore(score[1])
    }

    /**
     * Check board player met any condition, if not then tie the game.
     */
    private fun checkFullBoard(): Boolean {
        for (x in 0..2) {
            for (y in 0..2) {
                if (board[x][y] == 0) return false
            }
        }

        return true
    }
}