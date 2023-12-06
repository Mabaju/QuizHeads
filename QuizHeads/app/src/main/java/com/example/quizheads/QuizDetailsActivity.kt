package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.component.QuizDetails
import com.example.quizheads.person_api.Api
import com.example.quizheads.ui.theme.QuizHeadsTheme
import com.google.firebase.auth.FirebaseAuth
import com.example.quizheads.firebase.User
class QuizDetailsActivity : ComponentActivity() {
    val api = Api()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizHeadsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Button(modifier = Modifier.width(195.dp), onClick = {
                                val intent =
                                    Intent(this@QuizDetailsActivity, QuizActivity::class.java)

                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                                startActivity(intent)
                            }) {
                                Text(
                                    "Go back", fontSize = 20.sp, // Change font size
                                    color = Color.Black
                                ) // Change text color
                            }

                            Button(onClick = {
                                val intent =
                                    Intent(this@QuizDetailsActivity, MainActivity::class.java)

                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                                startActivity(intent)
                            }) {
                                Text(
                                    "Back to Frontpage", fontSize = 20.sp, // Change font size
                                    color = Color.Black // Change text color
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        QuizDetails(api.getQuizById(intent.getStringExtra("id") ?: ""),
                            onLastClick = { correctAnswersCount ->
                                val user = User.getInstance(FirebaseAuth.getInstance().currentUser!!.uid)

                                // Konverter correctAnswersCount til Int, hvis det er nødvendigt
                                val correctAnswersCountInt = correctAnswersCount.toIntOrNull() ?: 0
                                user.updateScore(correctAnswersCountInt)

                                val intent = Intent(this@QuizDetailsActivity, QuizResultActivity::class.java)
                                startActivity(intent)
                            })

                    }
                }
            }
        }
    }
}

