package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.quizheads.component.FriendsDetails
import com.example.quizheads.firebase.User
import com.example.quizheads.ui.theme.QuizHeadsTheme
import com.google.firebase.firestore.FirebaseFirestore

class FriendsDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val friendId = intent.getStringExtra("id") ?: return
        var friend: User? = null

        FirebaseFirestore.getInstance().collection("users").document(friendId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    friend = User(
                        userId = friendId,
                        email = document.getString("email") ?: "",
                        firstName = document.getString("firstName") ?: "",
                        lastName = document.getString("lastName") ?: "",
                        quizzesTaken = document.getLong("quizzesTaken")?.toInt() ?: 0,
                        totalScore = document.getLong("totalScore")?.toInt() ?: 0
                    )
                }
            }
            .addOnFailureListener {
                // Håndter fejl
            }

        setContent {
            QuizHeadsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row {
                            Button(onClick = {
                                val intent =
                                    Intent(this@FriendsDetailsActivity, FriendsActivity::class.java)
                                startActivity(intent)
                            }) {
                                Text(
                                    "Go back",
                                    fontSize = 20.sp, // Change font size
                                    color = Color.Black
                                ) // Change text color

                            }
                            Button(onClick = {
                                val intent =
                                    Intent(this@FriendsDetailsActivity, MainActivity::class.java)
                                startActivity(intent)
                            }) {
                                Text(
                                    "Back to Frontpage",
                                    fontSize = 20.sp, // Change font size
                                    color = Color.Black
                                ) // Change text color

                            }
                        }

                        FriendsDetails(friend)
                    }
                }
            }
        }
    }
}
