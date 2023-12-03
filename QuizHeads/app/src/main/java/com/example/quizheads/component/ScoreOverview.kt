package com.example.quizheads.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.quizheads.person_api.Person

@Composable

fun ScoreOverview(score: Person, onClick: (id:String)->Unit) {
    Column {
        Text(text = score.name, modifier = Modifier.clickable { onClick(score.id) })
    }
}