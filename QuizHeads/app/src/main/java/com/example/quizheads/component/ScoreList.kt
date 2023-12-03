package com.example.quizheads.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.quizheads.person_api.Person

@Composable
fun ScoreList(fetchScore:()->List<Person>, onClick: (id:String)->Unit){
    val score = remember{mutableStateOf(emptyList<Person>())}
    LaunchedEffect(key1 = Unit) {
        score.value = fetchScore()
    }

    Column {
        score.value.map{
            ScoreOverview(it, onClick = onClick)
        }
    }
}