package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.component.QuizList
import com.example.quizheads.person_api.Api
import com.example.quizheads.ui.theme.HeaderText
import com.example.quizheads.ui.theme.QuizHeadsTheme

class QuizActivity : ComponentActivity() {
    val api = Api()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizHeadsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Button(onClick = {
                            finish()
                        }) {
                            Text(
                                "Go back",
                                fontSize = 20.sp, // Change font size
                                color = Color.Black, // Change text color
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        HeaderText(text = "Quizes", Modifier.align(Alignment.CenterHorizontally))

                        Spacer(modifier = Modifier.height(20.dp))

                        QuizList(fetchQuiz = {
                            api.getQuiz()
                        }, onClick = {
                            val intent = Intent(this@QuizActivity, QuizDetailsActivity::class.java)

                            intent.putExtra("id", it)
                            startActivity(intent)
                        })

                    }
                }
            }
        }
    }
}

