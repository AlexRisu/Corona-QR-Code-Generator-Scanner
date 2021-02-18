package com.example.coronaqrcodegenerator


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaqrcodegenerator.models.Person
import com.google.zxing.integration.android.IntentIntegrator


class ScannerActivity : AppCompatActivity() {
    private lateinit var personAdapter: PersonRecyclerAdapter
    private var people = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        initRecyclerView()
        people.add(Person("jaber", "01456485"))

        personAdapter.notifyDataSetChanged()

    }


    private fun initRecyclerView() {
        val recycler = findViewById<RecyclerView>(R.id.recycler_view)
        recycler.apply {
            layoutManager = LinearLayoutManager(this@ScannerActivity)
            personAdapter = PersonRecyclerAdapter(people)
            adapter = personAdapter
        }

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
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}