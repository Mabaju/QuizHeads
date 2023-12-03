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
import com.example.quizheads.component.QuizDetails
import com.example.quizheads.person_api.Api
import com.example.quizheads.ui.theme.QuizHeadsTheme

class QuizDetailsActivity : ComponentActivity() {
    val api = Api()
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContent {
            QuizHeadsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row{
                            Button(onClick = {
                                val intent = Intent(this@QuizDetailsActivity, QuizActivity::class.java)
                                startActivity(intent)
                            }) {
                                Text("Go back",
                                    fontSize = 20.sp, // Change font size
                                    color = Color.Black) // Change text color

                            }
                            Button(onClick = {
                                val intent = Intent(this@QuizDetailsActivity, MainActivity::class.java)
                                startActivity(intent)
                            }) {
                                Text("Back to Frontpage",
                                    fontSize = 20.sp, // Change font size
                                    color = Color.Black) // Change text color

                            }
                        }


                        QuizDetails{
                            api.getQuiz(intent.getStringExtra("id")?:"")
                        }
                    }
                }
            }
        }
    }
}

