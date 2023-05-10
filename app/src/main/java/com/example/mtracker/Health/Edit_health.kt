package com.example.mtracker.Health

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
import com.example.mtracker.Models.HealthModel
import com.example.mtracker.R
import com.google.firebase.database.FirebaseDatabase

class Edit_health : AppCompatActivity() {
    private lateinit var tvHealthId: TextView
    private lateinit var healthamount: TextView
    private lateinit var healthedtbtn: AppCompatImageButton
    private lateinit var healthdltbtn: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_health)

        initView()
        setValuesToViews()


        healthedtbtn.setOnClickListener {
            openUpdateHealth(
                intent.getStringExtra("billId").toString(),
                intent.getStringExtra("bills").toString()
            )
        }


        healthdltbtn.setOnClickListener {
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
        tvHealthId = findViewById(R.id.tvHealthId)
        healthamount = findViewById(R.id.healthamount)

        healthedtbtn = findViewById(R.id.healthedtbtn)
        healthdltbtn = findViewById(R.id.healthdltbtn)
    }

    private fun setValuesToViews() {
        tvHealthId.text = intent.getStringExtra("billId")
        healthamount.text = intent.getStringExtra("bills")
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Health").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Health data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, HealthFetch::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateHealth(
        healthId: String,
        healths: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updated_health, null)

        mDialog.setView(mDialogView)

        val etHealth = mDialogView.findViewById<EditText>(R.id.etHealth)


        val healthUpdateData = mDialogView.findViewById<Button>(R.id.healthUpdateData)

        etHealth.setText(intent.getStringExtra("bills").toString())


        mDialog.setTitle("Updating $healths Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        healthUpdateData.setOnClickListener {
            updateEmpData(
                healthId,
                etHealth.text.toString()
            )

            Toast.makeText(applicationContext, "Health Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            healthamount.text = etHealth.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateEmpData(
        healthId: String,
        healthName: String,

        ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Health").child(healthId)
        val empInfo = HealthModel(healthId, healthName)
        dbRef.setValue(empInfo)
    }
}