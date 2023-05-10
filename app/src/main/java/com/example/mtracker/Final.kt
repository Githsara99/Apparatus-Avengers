package com.example.mtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Final : AppCompatActivity() {
    private lateinit var enterBill : EditText
    private lateinit var enterEducation : EditText
    private lateinit var enterFood : EditText
    private lateinit var enterHealth : EditText
    private lateinit var enterFuel : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)



        FirebaseApp.initializeApp(this)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        val databaseReference = FirebaseDatabase.getInstance().getReference("income")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentIncome = snapshot.getValue(Int::class.java) ?: 0
                // Use the currentIncome value to calculate the balance
                val billAmount = enterBill.text.toString().toIntOrNull() ?: 0
                val foodAmount = enterFood.text.toString().toIntOrNull() ?: 0
                val healthAmount = enterHealth.text.toString().toIntOrNull() ?: 0
                val educationAmount = enterEducation.text.toString().toIntOrNull() ?: 0
                val fuelAmount = enterFuel.text.toString().toIntOrNull() ?: 0
                val totalSpent = billAmount + foodAmount + healthAmount + educationAmount + fuelAmount
                val balance = currentIncome - totalSpent
                val balanceTextView = findViewById<TextView>(R.id.balanceTextView)
                balanceTextView.text = "Balance: $balance"
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })


    }
}