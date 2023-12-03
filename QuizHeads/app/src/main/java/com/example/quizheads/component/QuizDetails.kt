package com.example.quizheads.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import com.example.quizheads.person_api.Quiz

@Composable
fun QuizDetails(fetchQuiz: ()-> Quiz?) {
    val quiz = remember{ mutableStateOf<Quiz?>(null) }
    LaunchedEffect(key1 = Unit) {
        quiz.value = fetchQuiz()
    }

    Column {
        Text(text = quiz.value?.name.toString(),
            fontSize = 30.sp)
        Text(text = "Spørgsmål her...")
    }
}