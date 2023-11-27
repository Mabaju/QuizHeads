package com.example.quizheads.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.quizheads.person_api.Quiz

@Composable
fun QuizList(fetchQuiz: () -> List<Quiz>, onClick: (id: String) -> Unit) {

    val quiz = remember { mutableStateOf(emptyList<Quiz>()) }
    LaunchedEffect(key1 = Unit) {
        quiz.value = fetchQuiz()
    }

    Column {
        quiz.value.map {
            QuizOverview(it, onClick = onClick)
        }
    }
}