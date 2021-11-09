package com.example.ajmaintest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main.*

class MainActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val userName:String = intent.getStringExtra("userName").toString()
        username.text ="Hello $userName"

    }

    }
