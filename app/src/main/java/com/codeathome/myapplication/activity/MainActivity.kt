package com.codeathome.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeathome.myapplication.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMovie.setOnClickListener {
            Intent(this@MainActivity, MovieActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}