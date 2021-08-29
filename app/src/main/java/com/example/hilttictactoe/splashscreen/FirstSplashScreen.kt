package com.example.hilttictactoe.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.hilttictactoe.MainActivity
import com.example.hilttictactoe.R
import kotlinx.android.synthetic.main.activity_first_splash_screen.*

class FirstSplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_splash_screen)

        firstSplashScreenImage.alpha = 0f
        firstSplashScreenImage.animate().setDuration(2500).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }


    }
}