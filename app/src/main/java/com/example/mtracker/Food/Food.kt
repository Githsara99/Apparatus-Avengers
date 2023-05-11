package com.example.mtracker.Food

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

class Food : AppCompatActivity() {
    private lateinit var enterFood : EditText
    private lateinit var dbRef : DatabaseReference
    private lateinit var addFoodBtn : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        enterFood = findViewById(R.id.enterFood)
        addFoodBtn = findViewById(R.id.addFoodBtn)
        dbRef = FirebaseDatabase.getInstance().getReference("Food")


        addFoodBtn.setOnClickListener {
            saveFoodData()
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
        val foodSummary: Button = findViewById(R.id.foodSummary)
        foodSummary.setOnClickListener {
            val intent = Intent(this, FoodFetch::class.java)
            startActivity(intent)
        }

    }

    private fun saveFoodData(){

        val foodAmount = enterFood.text.toString()

        if (foodAmount.isEmpty()) {
            enterFood.error = "Please enter amount"
        }

        //to handle the overlapping of user entered data
        val foodId = dbRef.push().key!!

        val food = BillModel(foodId, foodAmount)

        dbRef.child(foodId).setValue(food)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                enterFood.text.clear()

                val intent = Intent(this, FoodFetch::class.java)
                startActivity(intent)


            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}