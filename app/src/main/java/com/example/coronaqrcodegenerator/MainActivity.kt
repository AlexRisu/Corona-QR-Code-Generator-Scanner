package com.example.coronaqrcodegenerator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun routeToCodeCreator(view: View) {
        val qrCodeCreatorIntent: Intent = Intent(this, QRCodeGeneratorActivity::class.java);
        startActivity(qrCodeCreatorIntent);
    }

    fun routeToCodeScanner(view: View) {
        println("Scanner")
    }
}