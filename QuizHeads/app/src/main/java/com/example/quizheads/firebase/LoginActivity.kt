package com.example.quizheads.firebase

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

import com.google.firebase.auth.FirebaseAuth
import com.example.quizheads.R  // Erstat med din korrekte R import, hvis nødvendigt
import com.example.quizheads.MainActivity
import com.example.quizheads.firebase.User
class LoginActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)  // Sørg for at R refererer til din korrekte R-klasse

        // Initialiser Firebase Auth
        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        // Opret en User instans
                        userId?.let { User.getInstance(it) }

                        // Start MainActivity
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }

        val signupButton = findViewById<Button>(R.id.signupButton)
        signupButton.setOnClickListener {
            // Start SignupActivity
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}
