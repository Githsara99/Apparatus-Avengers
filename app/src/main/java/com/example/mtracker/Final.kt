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
//    private lateinit var enterBill : EditText
//    private lateinit var enterEducation : EditText
//    private lateinit var enterFood : EditText
//    private lateinit var enterHealth : EditText
//    private lateinit var enterFuel : EditText
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_final)
//
//
//        FirebaseApp.initializeApp(this)
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
//        val dbRef = FirebaseDatabase.getInstance().getReference("income")
//        dbRef.keepSynced(true)
//
//
//        val inflater = layoutInflater
//        val layout1 = inflater.inflate(R.layout.activity_bill, null)
//        val layout2 = inflater.inflate(R.layout.activity_education, null)
//        val layout3 = inflater.inflate(R.layout.activity_food, null)
//        val layout4 = inflater.inflate(R.layout.activity_hospital, null)
//        val layout5 = inflater.inflate(R.layout.activity_fuel, null)
//
//        enterBill = layout1.findViewById(R.id.enterBill)
//        enterEducation = layout2.findViewById(R.id.enterEducation)
//        enterFood = layout3.findViewById(R.id.enterFood)
//        enterHealth = layout4.findViewById(R.id.enterHealth)
//        enterFuel = layout5.findViewById(R.id.enterFuel)
//
//
//
//
//        dbRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val currentIncome = snapshot.getValue(String::class.java)
//                // Use the currentIncome value to calculate the balance
//                val billAmount = enterBill.text.toString().toDoubleOrNull() ?: 0.0
//                val foodAmount = enterFood.text.toString().toDoubleOrNull() ?: 0.0
//                val healthAmount = enterHealth.text.toString().toDoubleOrNull() ?: 0.0
//                val educationAmount = enterEducation.text.toString().toDoubleOrNull() ?: 0.0
//                val fuelAmount = enterFuel.text.toString().toDoubleOrNull() ?: 0.0
//                val totalSpent = billAmount + foodAmount + healthAmount + educationAmount + fuelAmount
//                val balance = currentIncome?.toDouble()?.minus(totalSpent) ?: 0.0
//                val balanceTextView = findViewById<TextView>(R.id.balanceTextView)
//                balanceTextView.text = "Balance: $balance"
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle the error
//            }
//        })
//
//
//    }
}
