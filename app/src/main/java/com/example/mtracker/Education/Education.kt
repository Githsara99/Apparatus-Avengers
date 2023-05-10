package com.example.mtracker.Education

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
import com.example.mtracker.Fuel.FuelFetch
import com.example.mtracker.Models.BillModel
import com.example.mtracker.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Education : AppCompatActivity() {
    private lateinit var enterEducation : EditText
    private lateinit var dbRef : DatabaseReference
    private lateinit var addEducationBtn : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        enterEducation = findViewById(R.id.enterEducation)
        addEducationBtn = findViewById(R.id.addEducationBtn)
        dbRef = FirebaseDatabase.getInstance().getReference("Education")


        addEducationBtn.setOnClickListener {
            saveBillData()
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
        val eduSummary: Button = findViewById(R.id.eduSummary)
        eduSummary.setOnClickListener {
            val intent = Intent(this, EduFetch::class.java)
            startActivity(intent)
        }

    }

    private fun saveBillData(){

        val eduAmount = enterEducation.text.toString()

        if (eduAmount.isEmpty()) {
            enterEducation.error = "Please enter amount"
        }

        //to handle the overlapping of user entered data
        val eduId = dbRef.push().key!!

        val edu = BillModel(eduId, eduAmount)

        dbRef.child(eduId).setValue(edu)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                enterEducation.text.clear()

                val intent = Intent(this, EduFetch::class.java)
                startActivity(intent)


            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}