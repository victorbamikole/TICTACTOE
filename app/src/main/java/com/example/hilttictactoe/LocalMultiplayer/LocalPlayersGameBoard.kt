package com.example.hilttictactoe.LocalMultiplayer

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.hilttictactoe.R


class LocalPlayersGameBoard : AppCompatActivity() {
    lateinit var buttons:Array<Array<ImageButton>>
    lateinit var textViewPlayer1:TextView
    lateinit var textViewPlayer2:TextView

    private var player1Turn:Boolean = true
    private var roundCount:Int = 0
    private var player1Points:Int = 0
    private var player2Points:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_players_game_board)

        textViewPlayer1 = findViewById(R.id.player1Textview)
        textViewPlayer2 = findViewById(R.id.player2Textview)

//        val resetGameBtn:Button = findViewById(R.id.resetGameBtn)
        val resetGameBtn:Button = findViewById(resources.getIdentifier("resetGameBtn", "id", packageName))

        //reset button to reset game
        resetGameBtn.setOnClickListener {
            player1Points = 0
            player2Points = 0
            updateScore()
            clearBoard()
        }


        /**a two dimensional array that contais all the gameboard buttons thru the initButtons
         * function getting the row(r) and column (c) into this variable to assign the values (0-3) to it**/
        buttons = Array(3){r ->
            Array(3){c ->
                initButtons(r, c)
            }
        }
    }


    //get each button programmatically with r and c variables
    //to get multiple buttons to do something similar and  this is how to find each of them by id.
    private fun initButtons(r: Int, c: Int):ImageButton {
        val btn:ImageButton = findViewById(resources.getIdentifier("btn$r$c", "id", packageName))
        btn.setOnClickListener {
            onBtnClicked(btn)
        }
        return btn
    }

    private fun onBtnClicked(btn: ImageButton){
        //checks if the box has already been clicked, does nothing when clicked if it has already been clicked
        if (btn.drawable != null) return
        if (player1Turn){
            btn.setImageResource(R.drawable.xicon)
        }else{
            btn.setImageResource(R.drawable.oicon)
        }
        //to count the amount of button clicked
        roundCount++

        if(checkForWin()){ //if a player wins after all boxes have been filled
            if(player1Turn){
                return win(1)
            }
            win(2)
        }else if (roundCount == 9){ //if no one wins after all boxes have been filled
            draw()
        }else{
            //to alternate the players' turn when there are still boxes to fill
            player1Turn = !player1Turn
        }

    }

    //kinda confused about this
    private fun checkForWin(): Boolean {
        val fields = Array(3){r->
            Array(3){c->
                getField(buttons[r][c])
            }
        }


        for(i in 0..2){
            //checks if we have a column win
            if ((fields[i][0] ==  fields[i][1]) && (fields[i][0] ==  fields[i][2]) && fields[i][0] != null){
                return true
            }else if ((fields[0][i] ==  fields[1][i]) && (fields[0][i] ==  fields[2][i]) && fields[0][i] != null){ //checks if we have a row win
                return true
            }
        }
        //checks if we have a diagonal win
        if ((fields[0][0] ==  fields[1][1]) && (fields[0][0] ==  fields[2][2]) && fields[0][0] != null){
            return true
        }else if((fields[0][2] ==  fields[1][1]) && (fields[0][2] ==  fields[2][0]) && fields[0][2] != null){
            return true
        }
        return false

    }

    private fun win(player: Int) {
        if (player == 1) player1Points++ else player2Points++
        Toast.makeText(this, "player $player won!", Toast.LENGTH_SHORT).show()
        updateScore()
        clearBoard()
    }

    private fun draw() {
        Toast.makeText(this, "It's a tie!", Toast.LENGTH_SHORT).show()
        updateScore()
        clearBoard()
    }
    
    //to reset the whole game
    private fun clearBoard() {
        for (i in 0..2){
            for (j in 0..2){
                buttons[i][j].setImageResource(0) //removes the image resource
            }
        }
        roundCount = 0
        player1Turn = true
    }

    private fun getField(btn: ImageButton) :Char?{
        val drw:Drawable? = btn.drawable
        val drwX:Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.xicon, null)
        val drwO:Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.oicon, null)

        return when(drw?.constantState){
            drwX?.constantState -> 'X'
            drwO?.constantState -> 'O'
            else -> null
        }
    }

    //to change players' score
    private fun updateScore() {
        textViewPlayer1.text = "PLAYER 1: $player1Points"
        textViewPlayer2.text = "PLAYER 2: $player2Points"
    }


}