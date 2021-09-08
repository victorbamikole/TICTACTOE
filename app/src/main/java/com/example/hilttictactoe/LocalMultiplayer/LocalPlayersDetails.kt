package com.example.hilttictactoe.LocalMultiplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hilttictactoe.R
import kotlinx.android.synthetic.main.activity_local_playersdetails.*

class LocalPlayersDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_playersdetails)

        startGameButton.setOnClickListener {
            startActivity(Intent(this@LocalPlayersDetails, LocalPlayersGameBoard::class.java))
        }
    }
}