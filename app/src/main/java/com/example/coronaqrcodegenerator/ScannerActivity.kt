package com.example.coronaqrcodegenerator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaqrcodegenerator.models.Person
import com.example.coronaqrcodegenerator.models.persistence.PersonDbHelper
import com.google.zxing.integration.android.IntentIntegrator


class ScannerActivity : AppCompatActivity() {
    private val personDbHelper = PersonDbHelper(this);
    private lateinit var personAdapter: PersonRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recycler = findViewById<RecyclerView>(R.id.recycler_view)
        recycler.apply {
            layoutManager = LinearLayoutManager(this@ScannerActivity)
            personAdapter = PersonRecyclerAdapter(personDbHelper)
            adapter = personAdapter
        }
        personAdapter.updateRecyclerView()
    }

    fun scanQRCode(view: View) {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                if (isUser(result.contents)) {
                    insertUserIntoDb(result.contents)
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun isUser(contents: String): Boolean {
        return contents.toCharArray().contains('|')
    }

    private fun insertUserIntoDb(contents: String) {
        val personString = contents.split("|");
        val userName = personString[0];
        val phoneNumber = personString[1];
        val toBeInsertedPerson = Person(userName, phoneNumber);

        personDbHelper.insertSingle(toBeInsertedPerson);
        personAdapter.updateRecyclerView()
    }
}