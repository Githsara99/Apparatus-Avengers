package com.example.mtracker.Food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageButton
import com.example.mtracker.Bill.BillFetch
import com.example.mtracker.Category
import com.example.mtracker.Final
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.FoodModel
import com.example.mtracker.R
import com.google.firebase.database.FirebaseDatabase

class Edit_food : AppCompatActivity() {
    private lateinit var tvFoodId: TextView
    private lateinit var foodamount: TextView
    private lateinit var foodedtbtn: AppCompatImageButton
    private lateinit var fooddltbtn: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_food)

        initView()
        setValuesToViews()


        foodedtbtn.setOnClickListener {
            openUpdateFood(
                intent.getStringExtra("billId").toString(),
                intent.getStringExtra("bills").toString()
            )
        }


        fooddltbtn.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("billId").toString()
            )
        }

        val home: ImageButton = findViewById(R.id.homebtn)
        home.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }
        val cat: ImageButton = findViewById(R.id.catbtn)
        cat.setOnClickListener {
            val intent = Intent(this, Final::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
        tvFoodId = findViewById(R.id.tvFoodId)
        foodamount = findViewById(R.id.foodamount)

        foodedtbtn = findViewById(R.id.foodedtbtn)
        fooddltbtn = findViewById(R.id.fooddltbtn)
    }

    private fun setValuesToViews() {
        tvFoodId.text = intent.getStringExtra("billId")
        foodamount.text = intent.getStringExtra("bills")
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Food").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Food data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FoodFetch::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateFood(
        foodId: String,
        foods: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updated_food, null)

        mDialog.setView(mDialogView)

        val etFood = mDialogView.findViewById<EditText>(R.id.etFood)


        val btnUpdateData = mDialogView.findViewById<Button>(R.id.foodUpdateData)

        etFood.setText(intent.getStringExtra("bills").toString())


        mDialog.setTitle("Updating $foods Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                foodId,
                etFood.text.toString()
            )

            Toast.makeText(applicationContext, "Food Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            foodamount.text = etFood.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateEmpData(
        foodId: String,
        foodName: String,

        ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Education").child(foodId)
        val empInfo = FoodModel(foodId, foodName)
        dbRef.setValue(empInfo)
    }
}