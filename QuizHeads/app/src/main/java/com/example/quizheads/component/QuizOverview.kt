package com.example.quizheads.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.quizheads.person_api.Quiz


@Composable

fun QuizOverview(quiz: Quiz, onClick: (id: String) -> Unit) {
    Column {
        Text(text = quiz.name,
            modifier = Modifier.clickable { onClick(quiz.id) },
            fontSize = 20.sp
        )
    }
}