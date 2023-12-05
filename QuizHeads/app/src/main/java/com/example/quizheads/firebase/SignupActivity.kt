package com.example.quizheads.firebase

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.util.Log

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.example.quizheads.R
import com.example.quizheads.MainActivity
class SignupActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val signupButton = findViewById<Button>(R.id.signupButton)

        signupButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid ?: ""
                        val userEmail = email // E-mail indtastet af brugeren
                        val firstName = findViewById<EditText>(R.id.firstNameEditText).text.toString().trim()
                        val lastName = findViewById<EditText>(R.id.lastNameEditText).text.toString().trim()

                        val user = User(userId, userEmail, firstName, lastName, 0, 0)
                        FirebaseFirestore.getInstance().collection("users").document(userId).set(user)
                            .addOnSuccessListener {
                                // Bruger oprettet og gemt i Firestore
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                            .addOnFailureListener { e ->
                                // Log fejlen for detaljeret fejlfinding
                                Log.e("FirestoreError", "Error writing document", e)

                                // Vis en Toast med fejlbesked
                                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                    } else {
                        // Hvis tilmelding fejler, vis en besked til brugeren.
                        Toast.makeText(this, "Signup failed.", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}

