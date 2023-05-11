package com.example.mtracker.Fuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.mtracker.Bill.BillFetch
import com.example.mtracker.Category
import com.example.mtracker.Final
import com.example.mtracker.Models.BillModel
import com.example.mtracker.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Fuel : AppCompatActivity() {
    private lateinit var enterFuel : EditText
    private lateinit var dbRef : DatabaseReference
    private lateinit var addFuelBtn : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel)

        enterFuel = findViewById(R.id.enterFuel)
        addFuelBtn = findViewById(R.id.addFuelBtn)
        dbRef = FirebaseDatabase.getInstance().getReference("Fuel")


        addFuelBtn.setOnClickListener {
            saveFuelData()
        }

        val home: ImageButton = findViewById(R.id.home)
        home.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }
        val cat: ImageButton = findViewById(R.id.category)
        cat.setOnClickListener {
            val intent = Intent(this, Final::class.java)
            startActivity(intent)
        }
        val fuelSummary: Button = findViewById(R.id.fuelSummary)
        fuelSummary.setOnClickListener {
            val intent = Intent(this, FuelFetch::class.java)
            startActivity(intent)
        }

    }

    private fun saveFuelData(){

        val fuelAmount = enterFuel.text.toString()

        if (fuelAmount.isEmpty()) {
            enterFuel.error = "Please enter amount"
        }

        //to handle the overlapping of user entered data
        val fuelId = dbRef.push().key!!

        val fuel = BillModel(fuelId, fuelAmount)

        dbRef.child(fuelId).setValue(fuel)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                enterFuel.text.clear()

                val intent = Intent(this, FuelFetch::class.java)
                startActivity(intent)


            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}