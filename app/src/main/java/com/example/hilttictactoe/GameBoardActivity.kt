package com.example.hilttictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game_board.*
import kotlin.random.Random

class GameBoardActivity : AppCompatActivity() {
    //variable that holds our board design 2d array
    private val boardCells = Array(3) { arrayOfNulls<ImageView>(3) }
    var board = Board()

    @SuppressLint("SetTextI18n")//this annotation supppresses the funtion that works but still shows error
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_board)

        loadBoard()

        buttonRestart.setOnClickListener {
            board = Board()
            mapBoardToUi()

        }

        var first = intent.getStringExtra("firstPlayer")
        var second = intent.getStringExtra("secondPlayer")

//        firstPerson.text = "$first: %1\$d"
//        secondPerson.text = "$second: %1\$d"

    }

    //This function maps our board to UI
    private fun mapBoardToUi() {
        //we loop through our board now
        for (i in board.board.indices) {
            for (j in board.board.indices) {
                when (board.board[i][j]) {
                    //Here we will check if element is computer or player or anything else
                    Board.PLAYER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false

                    }
                    Board.COMPUTER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled = false

                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true

                    }

                }
            }
        }

    }

    private fun loadBoard() {
        //Initialize our ImageView of the 2d array
        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 250
                    height = 230
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.brand
                    )
                )
                //Attach the cellClickListener to our image view
                boardCells[i][j]?.setOnClickListener(CellClickListener(i, j))
                layout_board.addView(boardCells[i][j])
            }
        }


    }

    /** We need to add a ClickListener to the buttons, but we need to get the index of the cell when a button is clicked, so we have to create
     *  a custom class and pass in the index as parameters */
    inner class CellClickListener(private val i: Int, private val j: Int) :
        android.view.View.OnClickListener {
        override fun onClick(v: View?) {
            //We will get the cell index first
            val cell = Cell(i, j)
            board.placeMove(cell, Board.PLAYER)

            if (board.availableCells.isNotEmpty()) {
                //This gives us a random cell from the available cells
                val cCell = board.availableCells[Random.nextInt(0, board.availableCells.size)]
//                val cCell = board.availableCells
                board.placeMove(cCell, Board.COMPUTER)
            }
            mapBoardToUi()

        }

    }

    private fun chooseWinner(){

    }

}