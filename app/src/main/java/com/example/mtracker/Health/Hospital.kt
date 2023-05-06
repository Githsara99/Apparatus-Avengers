package com.example.mtracker.Health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.mtracker.Bill.BillFetch
import com.example.mtracker.Category
import com.example.mtracker.Models.BillModel
import com.example.mtracker.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Hospital : AppCompatActivity() {
    private lateinit var enterHealth : EditText
    private lateinit var dbRef : DatabaseReference
    private lateinit var addHealthBtn : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital)

        enterHealth = findViewById(R.id.enterHealth)
        addHealthBtn = findViewById(R.id.addHealthBtn)
        dbRef = FirebaseDatabase.getInstance().getReference("Health")


        addHealthBtn.setOnClickListener {
            saveBillData()
        }

        val home: ImageButton = findViewById(R.id.homebtn)
        home.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }
        val cat: ImageButton = findViewById(R.id.catbtn)
        cat.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }

    }

    private fun saveBillData(){

        val healthAmount = enterHealth.text.toString()

        if (healthAmount.isEmpty()) {
            enterHealth.error = "Please enter amount"
        }

        //to handle the overlapping of user entered data
        val healthId = dbRef.push().key!!

        val health = BillModel(healthId, healthAmount)

        dbRef.child(healthId).setValue(health)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                enterHealth.text.clear()

                val intent = Intent(this, HealthFetch::class.java)
                startActivity(intent)


            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}