package com.example.mtracker.Bill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.mtracker.Category
import com.example.mtracker.Models.BillModel
import com.example.mtracker.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Bill : AppCompatActivity() {
    private lateinit var enterBill : EditText
    private lateinit var dbRef : DatabaseReference
    private lateinit var addBillBtn : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)

        enterBill = findViewById(R.id.enterBill)
        addBillBtn = findViewById(R.id.addBillBtn)
        dbRef = FirebaseDatabase.getInstance().getReference("Bill")


        addBillBtn.setOnClickListener {
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

        val billAmount = enterBill.text.toString()

        if (billAmount.isEmpty()) {
            enterBill.error = "Please enter amount"
        }

        //to handle the overlapping of user entered data
        val billId = dbRef.push().key!!

        val bill = BillModel(billId, billAmount)

        dbRef.child(billId).setValue(bill)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                enterBill.text.clear()

                val intent = Intent(this, BillFetch::class.java)
                startActivity(intent)


            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}