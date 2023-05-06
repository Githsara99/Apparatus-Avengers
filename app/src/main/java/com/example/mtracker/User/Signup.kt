package com.example.mtracker.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mtracker.Category
import com.example.mtracker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val loginText: TextView = findViewById(R.id.textView_login_now)
        loginText.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val registerButton: Button = findViewById(R.id.btnSignup)

        //lets get email and password from the user
        registerButton.setOnClickListener {
            performSignUp()
        }

    }

    private fun performSignUp() {
        val name = findViewById<EditText>(R.id.enterName)
        val email = findViewById<EditText>(R.id.enterEmail)
        val password = findViewById<EditText>(R.id.enterPassword)
        val repassword = findViewById<EditText>(R.id.reEnterPassword)

        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputName = name.text.toString()
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        val inputRePassword = repassword.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, let move to the next activity i.e MainActivity

                    val intent = Intent(this, Category::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext,
                        "Success.",
                        Toast.LENGTH_SHORT,
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }

            .addOnFailureListener {
                Toast.makeText(this, "Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }

    }
}