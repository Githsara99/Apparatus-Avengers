package com.example.mtracker.Bill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageButton
import com.example.mtracker.Category
import com.example.mtracker.Models.BillModel
import com.example.mtracker.R
import com.google.firebase.database.FirebaseDatabase

class Edit_bill : AppCompatActivity() {
    private lateinit var tvBillId: TextView
    private lateinit var billamount: TextView
    private lateinit var billedtbtn: AppCompatImageButton
    private lateinit var billdltbtn: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bill)

        initView()
        setValuesToViews()


        billedtbtn.setOnClickListener {
            openUpdateBill(
                intent.getStringExtra("billId").toString(),
                intent.getStringExtra("bills").toString()
            )
        }


        billdltbtn.setOnClickListener {
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
        tvBillId = findViewById(R.id.tvBillId)
        billamount = findViewById(R.id.billamount)

        billedtbtn = findViewById(R.id.billedtbtn)
        billdltbtn = findViewById(R.id.billdltbtn)
    }

    private fun setValuesToViews() {
        tvBillId.text = intent.getStringExtra("billId")
        billamount.text = intent.getStringExtra("bills")
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Bill").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Bill data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, BillFetch::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateBill(
        billId: String,
        bills: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updated_bill, null)

        mDialog.setView(mDialogView)

        val etBill = mDialogView.findViewById<EditText>(R.id.etBill)


        val btnUpdateData = mDialogView.findViewById<Button>(R.id.billUpdateData)

        etBill.setText(intent.getStringExtra("bills").toString())


        mDialog.setTitle("Updating $bills Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                billId,
                etBill.text.toString()
            )

            Toast.makeText(applicationContext, "Bill Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            billamount.text = etBill.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateEmpData(
        billId: String,
        billName: String,

        ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Bill").child(billId)
        val empInfo = BillModel(billId, billName)
        dbRef.setValue(empInfo)
    }
}