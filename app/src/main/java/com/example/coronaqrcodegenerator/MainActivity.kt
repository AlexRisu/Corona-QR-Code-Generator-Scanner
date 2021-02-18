package com.example.coronaqrcodegenerator

import android.content.Context
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
        val generatorActivityIntent: Intent = Intent(this, GeneratorActivity::class.java);
        startActivity(generatorActivityIntent);
    }

    fun routeToCodeScanner(view: View) {
        val scannerActivityIntent: Intent = Intent(this, ScannerActivity::class.java);
        startActivity(scannerActivityIntent);
    }

    companion object {
        private var instance: MainActivity? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}