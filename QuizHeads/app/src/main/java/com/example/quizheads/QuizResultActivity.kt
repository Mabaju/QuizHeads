package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.person_api.Api
import com.example.quizheads.ui.theme.QuizHeadsTheme

var totalScore = 0

class QuizResultActivity : ComponentActivity() {
    val api = Api()


    private fun back() {
        val intent = Intent(this@QuizResultActivity, QuizActivity::class.java)

        // Add the following flags to clear the back stack up to ActivityA
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_SINGLE_TOP or
                Intent.FLAG_ACTIVITY_NEW_TASK

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val Result = intent.getIntExtra("quizResult", 0)
        val hasScore = intent.getBooleanExtra("HasScore", false)


        setContent {
            QuizHeadsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row {
                            Button(modifier = Modifier.width(195.dp),
                                onClick = {

                                    back()

                                }) {
                                Text(
                                    "New Quiz",
                                    fontSize = 20.sp, // Change font size
                                    color = Color.Black
                                ) // Change text color

                            }
                            Button(onClick = {
                                val intent =
                                    Intent(this@QuizResultActivity, MainActivity::class.java)

                                // Add the following flags to clear the back stack up to ActivityA
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                        Intent.FLAG_ACTIVITY_SINGLE_TOP or
                                        Intent.FLAG_ACTIVITY_NEW_TASK

                                startActivity(intent)

                            }) {
                                Text(
                                    "Back to Frontpage",
                                    fontSize = 20.sp, // Change font size
                                    color = Color.Black
                                ) // Change text color
                            }
                        }

                        if (hasScore) {
                            Text(
                                text = "Resultat",
                                fontSize = 100.sp, // Change font size
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            Spacer(modifier = Modifier.height(40.dp))

                            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                Text(
                                    text = "Quiz score:  ",
                                    fontSize = 50.sp, // Change font size
                                    //modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = Result.toString(),
                                    fontSize = 50.sp, // Change font size
                                    color = Color.Blue,
                                    //modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }

                            Spacer(modifier = Modifier.height(70.dp))
                        }

                        Text(
                            text = "Your total score",
                            fontSize = 50.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        totalScore = totalScore + Result
                        Text(
                            text = totalScore.toString(),
                            fontSize = 50.sp,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        back()
    }
}