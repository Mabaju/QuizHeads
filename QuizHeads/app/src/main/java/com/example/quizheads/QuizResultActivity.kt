package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.quizheads.firebase.User
import com.example.quizheads.ui.theme.QuizHeadsTheme

class QuizResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = User.getInstance("yourUserId").userId // Erstat "yourUserId" med den aktuelle brugers ID
        setContent {
            QuizHeadsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScoreScreen(userId) {
                        // Når brugeren trykker på "Go back", gå tilbage til MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreScreen(userId: String, onGoBack: () -> Unit) {
    val user = User.getInstance(userId)
    Column {
        Button(onClick = onGoBack) {
            Text("Go back",
                fontSize = 20.sp,
                color = Color.Black)
        }
        Text("Din Score",
            fontSize = 30.sp,
            color = Color.Black)

        Text("Antal quiz lavet: ${user.quizzesTaken}",
            fontSize = 20.sp,
            color = Color.Black)

        Text("Score: ${user.totalScore}",
            fontSize = 20.sp,
            color = Color.Black)
    }
}
