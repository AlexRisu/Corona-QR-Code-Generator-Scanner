package com.example.coronaqrcodegenerator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun routeToCodeCreator(view: View) {
        println("Creator")
    }

    fun routeToCodeScanner(view: View) {
        println("Scanner")
    }
}