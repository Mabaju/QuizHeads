package com.example.quizheads.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.person_api.Quiz
import com.example.quizheads.ui.theme.HeaderText
import com.example.quizheads.ui.theme.QuizHeadsTheme


@Composable
fun QuizDetails(
    fetchQuiz: Quiz?,
    onLastClick: (id: String) -> Unit,
    onSearchClick: (String) -> Unit,
    currentIndex: Int,
    onIndexChanged: (Int) -> Unit
) {
    val quiz = remember { mutableStateOf<Quiz?>(null) }
    LaunchedEffect(key1 = Unit) {
        quiz.value = fetchQuiz
    }

    var quizResult by remember {
        mutableStateOf(0)
    }

    var localCurrentIndex by remember {
        mutableStateOf(currentIndex)
    }



    Column {
        HeaderText(text = quiz.value?.name.toString(), Modifier.align(Alignment.CenterHorizontally)
            .height(120.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = quiz.value?.questions?.get(currentIndex)?.question ?: "",
            modifier = Modifier.height(100.dp),
            fontSize = 20.sp,
            lineHeight = 20.sp
            )

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            quiz.value?.questions?.get(currentIndex)?.question?.let { question ->
                onSearchClick(question)
            }
        }) {
            Text("Search for answer on wikipedia")
        }
        Spacer(modifier = Modifier.height(10.dp))


        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val buttonNr = 1
                if (buttonNr == quiz.value?.questions?.get(localCurrentIndex)?.correctAnswer) {
                    quizResult++
                }
                if (quiz.value?.questions?.size!! - 1 > localCurrentIndex) {
                    val newIndex = localCurrentIndex + 1
                    localCurrentIndex = newIndex
                    onIndexChanged(newIndex) // Opdater currentIndex og nulstil søgeresultaterne
                } else {
                    // Håndter afslutning af quiz
                    onLastClick(quizResult.toString())
                }
            }) {
            Text(text = quiz.value?.questions?.get(localCurrentIndex)?.answerPosibilities?.get(0) ?: "",
                fontSize = 15.sp)
        }


        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val buttonNr = 2
                if (buttonNr == quiz.value?.questions?.get(localCurrentIndex)?.correctAnswer) {
                    quizResult++
                }
                if (quiz.value?.questions?.size!! - 1 > localCurrentIndex) {
                    val newIndex = localCurrentIndex + 1
                    localCurrentIndex = newIndex
                    onIndexChanged(newIndex) // Opdater currentIndex og nulstil søgeresultaterne
                } else {
                    // Håndter afslutning af quiz
                    onLastClick(quizResult.toString())
                }
            }) {
            Text(text = quiz.value?.questions?.get(localCurrentIndex)?.answerPosibilities?.get(1) ?: "",
                fontSize = 15.sp)
        }

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val buttonNr = 3
                if (buttonNr == quiz.value?.questions?.get(localCurrentIndex)?.correctAnswer) {
                    quizResult++
                }
                if (quiz.value?.questions?.size!! - 1 > localCurrentIndex) {
                    val newIndex = localCurrentIndex + 1
                    localCurrentIndex = newIndex
                    onIndexChanged(newIndex) // Opdater currentIndex og nulstil søgeresultaterne
                } else {
                    // Håndter afslutning af quiz
                    onLastClick(quizResult.toString())
                }
            }) {
            Text(text = quiz.value?.questions?.get(localCurrentIndex)?.answerPosibilities?.get(2) ?: "",
                fontSize = 15.sp)
        }

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val buttonNr = 4
                if (buttonNr == quiz.value?.questions?.get(localCurrentIndex)?.correctAnswer) {
                    quizResult++
                }
                if (quiz.value?.questions?.size!! - 1 > localCurrentIndex) {
                    val newIndex = localCurrentIndex + 1
                    localCurrentIndex = newIndex
                    onIndexChanged(newIndex) // Opdater currentIndex og nulstil søgeresultaterne
                } else {
                    // Håndter afslutning af quiz
                    onLastClick(quizResult.toString())
                }
            }) {
            Text(text = quiz.value?.questions?.get(localCurrentIndex)?.answerPosibilities?.get(3) ?: "",
                fontSize = 15.sp)
        }

    }
}