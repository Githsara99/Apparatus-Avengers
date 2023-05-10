package com.example.mtracker.Education

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
import com.example.mtracker.Models.EduModel
import com.example.mtracker.R
import com.google.firebase.database.FirebaseDatabase

class Edit_education : AppCompatActivity() {
    private lateinit var tvEduId: TextView
    private lateinit var eduamount: TextView
    private lateinit var eduedtbtn: AppCompatImageButton
    private lateinit var edudltbtn: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_education)

        initView()
        setValuesToViews()


        eduedtbtn.setOnClickListener {
            openUpdateEdu(
                intent.getStringExtra("billId").toString(),
                intent.getStringExtra("bills").toString()
            )
        }


        edudltbtn.setOnClickListener {
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
        tvEduId = findViewById(R.id.tvEduId)
        eduamount = findViewById(R.id.eduamount)

        eduedtbtn = findViewById(R.id.eduedtbtn)
        edudltbtn = findViewById(R.id.edudltbtn)
    }

    private fun setValuesToViews() {
        tvEduId.text = intent.getStringExtra("billId")
        eduamount.text = intent.getStringExtra("bills")
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Education").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Education data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, EduFetch::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateEdu(
        eduId: String,
        edus: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updated_edu, null)

        mDialog.setView(mDialogView)

        val etEdu = mDialogView.findViewById<EditText>(R.id.etEdu)


        val btnUpdateData = mDialogView.findViewById<Button>(R.id.eduUpdateData)

        etEdu.setText(intent.getStringExtra("bills").toString())


        mDialog.setTitle("Updating $edus Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                eduId,
                etEdu.text.toString()
            )

            Toast.makeText(applicationContext, "Education Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            eduamount.text = etEdu.text.toString()

            alertDialog.dismiss()
        }
    }
    private fun updateEmpData(
        eduId: String,
        eduName: String,

        ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Education").child(eduId)
        val empInfo = EduModel(eduId, eduName)
        dbRef.setValue(empInfo)
    }
}