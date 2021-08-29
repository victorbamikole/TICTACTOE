package com.example.hilttictactoe

class Board {

    companion object {
        const val PLAYER = "O"
        const val COMPUTER = "X"
    }

    //Create a 2d array of string type and size 3
    val board = Array(3) { arrayOfNulls<String>(3)}

    //This variable provides a list of available cells
    val availableCells: List<Cell>

    get() {
        val cells = mutableListOf<Cell>()
        for (i in board.indices){
            for (j in board.indices){
                if (board[i][j].isNullOrEmpty()){
                    cells.add(Cell(i, j))
                }
            }
        }
        return cells
    }


    /**This functions makes the move for each player and we pass the cell where we need to place the move*
     * and we will pass the player whois playing and making the move
     */
    fun placeMove(cell: Cell, player: String){
        //We will get the index from cell 1 and j to equal player
        board[cell.i][cell.j] = player
    }


}