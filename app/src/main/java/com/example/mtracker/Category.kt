package com.example.mtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.mtracker.Bill.Bill
import com.example.mtracker.Education.Education
import com.example.mtracker.Food.Food
import com.example.mtracker.Fuel.Fuel
import com.example.mtracker.Health.HealthFetch
import com.example.mtracker.Health.Hospital
import com.example.mtracker.Models.BillModel
import com.example.mtracker.User.UserAccount
import com.example.mtracker.User.UserUpdate
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Category : AppCompatActivity() {
    private lateinit var enterIncome: EditText
    private lateinit var dbRef: DatabaseReference
    private lateinit var submitIncome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        enterIncome = findViewById(R.id.enter_income)
        submitIncome = findViewById(R.id.btnIncome)
        dbRef = FirebaseDatabase.getInstance().getReference("income")

        submitIncome.setOnClickListener {
            saveIncomeData()
        }
        val home: ImageButton = findViewById(R.id.home)
        home.setOnClickListener {
            val intent = Intent(this, Final::class.java)
            startActivity(intent)
        }

        val eduButton: ImageButton = findViewById(R.id.add_education)
        eduButton.setOnClickListener {
            val intent = Intent(this, Education::class.java)
            startActivity(intent)
        }

        val foodButton: ImageButton = findViewById(R.id.add_food)
        foodButton.setOnClickListener {
            val intent = Intent(this, Food::class.java)
            startActivity(intent)
        }

        val healthButton: ImageButton = findViewById(R.id.add_hospital)
        healthButton.setOnClickListener {
            val intent = Intent(this, Hospital::class.java)
            startActivity(intent)
        }

        val billButton: ImageButton = findViewById(R.id.add_bill)
        billButton.setOnClickListener {
            val intent = Intent(this, Bill::class.java)
            startActivity(intent)
        }

        val fuelButton: ImageButton = findViewById(R.id.add_fuel)
        fuelButton.setOnClickListener {
            val intent = Intent(this, Fuel::class.java)
            startActivity(intent)
        }
        val summaryButton: Button = findViewById(R.id.btnSummary)
        summaryButton.setOnClickListener {
            val intent = Intent(this, FinalPage::class.java)
            startActivity(intent)
        }
        val userButton: ImageButton = findViewById(R.id.user)
        userButton.setOnClickListener {
            val intent = Intent(this, UserAccount::class.java)
            startActivity(intent)
        }

    }

    private fun saveIncomeData() {

        val incomeAmount = enterIncome.text.toString()

        if (incomeAmount.isEmpty()) {
            enterIncome.error = "Please enter amount"
        }

        //to handle the overlapping of user entered data
        val incomeID = dbRef.push().key!!

        val income = BillModel(incomeID, incomeAmount)

        dbRef.child(incomeID).setValue(income)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show()

                enterIncome.text.clear()

//                val intent = Intent(this, Final::class.java)
//                startActivity(intent)


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}