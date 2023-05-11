package com.example.mtracker.Education

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtracker.Bill.BillAdapter
import com.example.mtracker.Bill.Edit_bill
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.EduModel
import com.example.mtracker.R
import com.google.firebase.database.*

class EduFetch : AppCompatActivity() {
    private lateinit var eduRecyclerView : RecyclerView
    private lateinit var rvEduData : TextView
    private lateinit var eduList : ArrayList<EduModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edu_fetch)

        eduRecyclerView = findViewById(R.id.rvEdu)
        eduRecyclerView.layoutManager = LinearLayoutManager(this)
        eduRecyclerView.setHasFixedSize(true)
        rvEduData = findViewById(R.id.rvEduData)

        eduList = arrayListOf<EduModel>()

        getEduData()
    }

    private fun getEduData() {
        eduRecyclerView.visibility = View.GONE
        rvEduData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Education")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eduList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(EduModel::class.java)
                        eduList.add(empData!!)
                    }
                    val mAdapter = EduAdapter(eduList)
                    eduRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : EduAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@EduFetch, Edit_education::class.java)

                            //put extras
                            intent.putExtra("billId",eduList[position].billId)
                            intent.putExtra("bills",eduList[position].bills)

                            startActivity(intent)
                        }

                    })

                    eduRecyclerView.visibility = View.VISIBLE
                    rvEduData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}