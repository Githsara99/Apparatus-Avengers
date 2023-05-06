package com.example.mtracker.Fuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageButton
import com.example.mtracker.Bill.BillFetch
import com.example.mtracker.Category
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.FuelModel
import com.example.mtracker.R
import com.google.firebase.database.FirebaseDatabase

class Edit_fuel : AppCompatActivity() {
    private lateinit var tvFuelId: TextView
    private lateinit var fuelamount: TextView
    private lateinit var fueledtbtn: AppCompatImageButton
    private lateinit var fueldltbtn: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_fuel)

        initView()
        setValuesToViews()


        fueledtbtn.setOnClickListener {
            openUpdateFuel(
                intent.getStringExtra("billId").toString(),
                intent.getStringExtra("bills").toString()
            )
        }


        fueldltbtn.setOnClickListener {
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
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
        tvFuelId = findViewById(R.id.tvFuelId)
        fuelamount = findViewById(R.id.fuelamount)

        fueledtbtn = findViewById(R.id.fueledtbtn)
        fueldltbtn = findViewById(R.id.fueldltbtn)
    }

    private fun setValuesToViews() {
        tvFuelId.text = intent.getStringExtra("billId")
        fuelamount.text = intent.getStringExtra("bills")
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Fuel").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Fuel data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FuelFetch::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateFuel(
        fuelId: String,
        fuels: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updated_fuel, null)

        mDialog.setView(mDialogView)

        val etfuel = mDialogView.findViewById<EditText>(R.id.etfuel)


        val btnUpdateData = mDialogView.findViewById<Button>(R.id.fuelUpdateData)

        etfuel.setText(intent.getStringExtra("bills").toString())


        mDialog.setTitle("Updating $fuels Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                fuelId,
                etfuel.text.toString()
            )

            Toast.makeText(applicationContext, "Fuel Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            fuelamount.text = etfuel.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateEmpData(
        fuelId: String,
        fuelName: String,

        ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Fuel").child(fuelId)
        val empInfo = FuelModel(fuelId, fuelName)
        dbRef.setValue(empInfo)
    }
}