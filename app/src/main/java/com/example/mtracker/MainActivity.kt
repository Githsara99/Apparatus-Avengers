package com.example.mtracker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mtracker.User.UserUpdate
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private var mDatabase: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, UserUpdate::class.java)
            startActivity(intent)

            finish()
        }, 3000)

        FirebaseApp.initializeApp(this);
        mDatabase = FirebaseDatabase.getInstance();
        mDatabase!!.setPersistenceEnabled(true);
    }
}