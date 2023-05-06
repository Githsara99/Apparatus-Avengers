package com.example.mtracker.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.mtracker.R
import com.example.mtracker.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserUpdate : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
//    private lateinit var binding: ActivityHomeBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        set view binding
//
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        auth = Firebase.auth
//
//        binding.btnUpdate.setOnClickListener{
//            val user = auth.currentUser
//            val password = binding.etPassword.text.toString()
//            if (checkPasswordField()){
//                user?.updatePassword(password)?.addOnCanceledListener {
//                    if(it.isSuccessful){
//                        Toast.makeText(this, "Updatesuccessfully", Toast.LENGTH_SHORT).show()
//                    }
//                    else{
//                        Log.e("error: ", it.exception.toString())
//                    }
//                }
//            }
//        }
//
//        binding.btnUpdate.setOnClickListener{
//            val user = auth.currentUser
//            val password = binding.etPassword.text.toString()
//            val email = binding.etEmail.text.toString()
//            if (checkPasswordField()){
//                user?.updatePassword(password)?.addOnCanceledListener {
//                    if(it.isSuccessful){
//                        Toast.makeText(this, "Update successfully", Toast.LENGTH_SHORT).show()
//                    }
//                    else{
//                        Log.e("error: ", it.exception.toString())
//                    }
//                }
//            }
//            if (checkEmailField()){
//                user?.updateEmail(email)?.addOnCanceledListener {
//                    if(it.isSuccessful){
//                        Toast.makeText(this, "Update successfully", Toast.LENGTH_SHORT).show()
//                    }
//                    else{
//                        Log.e("error: ", it.exception.toString())
//                    }
//                }
//            }
//        }
//
//    }
//    private fun checkPasswordField(): Boolean {
//        if(binding.etPassword.text.toString() == ""){
//            binding.textInputLayoutPassword.error = "This is required field"
//            binding.textInputLayoutPassword.errorIconDrawble = null
//            return false
//        }
//        if(binding.etPassword.text.length() <= 6){
//            binding.textInputLayoutPassword.error = "Password should at least 8 characters lang"
//            binding.textInputLayoutPassword.errorIconDrawble = null
//            return false
//        }
//        return true
//    }
//
//    private fun checkEmailField(): Boolean {
//        val email = binding.etEmail.text.toString()
//        if(binding.etEmail.text.toString() == ""){
//            binding.textInputLayoutPassword.error = "This is required field"
//            return false
//        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            binding.textInputLayoutPassword.error = "Check email format"
//
//            return false
//        }
//        return true
//    }
}