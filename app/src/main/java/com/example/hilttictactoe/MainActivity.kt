package com.example.hilttictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        comp.setOnClickListener {
//            var editFirstPlayer = editFirstPlayer.text.toString()
//            var editSecondPlayer = editSecondPlayer.text.toString()

            var intent = Intent(this@MainActivity, GameBoardActivity::class.java).also {
//                it.putExtra("firstPlayer", editFirstPlayer)
//                it.putExtra("secondPlayer", editSecondPlayer)
            }
            startActivity(intent)
        }
    }
}