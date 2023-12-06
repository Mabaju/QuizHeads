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
    onLastClick: (id: String) -> Unit
) {
    val quiz = remember { mutableStateOf<Quiz?>(null) }
    LaunchedEffect(key1 = Unit) {
        quiz.value = fetchQuiz
    }

    var quizResult by remember {
        mutableStateOf(0)
    }

    var currentIndex by remember {
        mutableStateOf(0)
    }


    Column {
        HeaderText(text = quiz.value?.name.toString(), Modifier.align(Alignment.CenterHorizontally)
            .height(160.dp))

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = quiz.value?.questions?.get(currentIndex)?.question ?: "",
            modifier = Modifier.height(200.dp),
            fontSize = 30.sp,
            lineHeight = 30.sp
            )

        Spacer(modifier = Modifier.height(40.dp))


        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
            val buttonNr = 1
            if (buttonNr == quiz.value?.questions?.get(currentIndex)?.correctAnswer) {
                quizResult++
            }
            Log.d("quizResult", "quizResult = " + quizResult)
            if (quiz.value?.questions?.size!! - 1 > currentIndex) {
                // Increment the currentQuestionIndex to move to the next question
                currentIndex++
                Log.d("currentindex", "current value = " + currentIndex)
            } else {
                Log.d("onLastClick", "Go to results")
                //Go to result
                onLastClick(quizResult.toString())
            }

        }) {
            Text(text = quiz.value?.questions?.get(currentIndex)?.answerPosibilities?.get(0) ?: "",
                fontSize = 20.sp)
        }

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
            val buttonNr = 2
            if (buttonNr == quiz.value?.questions?.get(currentIndex)?.correctAnswer) {
                quizResult++
            }
            Log.d("quizResult", "quizResult = " + quizResult)
            if (quiz.value?.questions?.size!! - 1 > currentIndex) {
                // Increment the currentQuestionIndex to move to the next question
                currentIndex++
                Log.d("currentindex", "current value = " + currentIndex)
            } else {
                Log.d("onLastClick", "Go to results")
                //Go to result
                onLastClick(quizResult.toString())
            }
        }) {
            Text(text = quiz.value?.questions?.get(currentIndex)?.answerPosibilities?.get(1) ?: "",
                fontSize = 20.sp)
        }

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
            val buttonNr = 3
            if (buttonNr == quiz.value?.questions?.get(currentIndex)?.correctAnswer) {
                quizResult++
            }
            Log.d("quizResult", "quizResult = " + quizResult)
            if (quiz.value?.questions?.size!! - 1 > currentIndex) {
                // Increment the currentQuestionIndex to move to the next question
                currentIndex++
                Log.d("currentindex", "current value = " + currentIndex)
            } else {
                Log.d("onLastClick", "Go to results")
                //Go to result
                onLastClick(quizResult.toString())
            }
        }) {
            Text(text = quiz.value?.questions?.get(currentIndex)?.answerPosibilities?.get(2) ?: "",
                fontSize = 20.sp)
        }

        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
            val buttonNr = 4
            if (buttonNr == quiz.value?.questions?.get(currentIndex)?.correctAnswer) {
                quizResult++
            }
            Log.d("quizResult", "quizResult = " + quizResult)
            if (quiz.value?.questions?.size!! - 1 > currentIndex) {
                // Increment the currentQuestionIndex to move to the next question
                currentIndex++
                Log.d("currentindex", "current value = " + currentIndex)
            } else {
                Log.d("onLastClick", "Go to results")
                //Go to result
                onLastClick(quizResult.toString())
            }
        }) {
            Text(text = quiz.value?.questions?.get(currentIndex)?.answerPosibilities?.get(3) ?: "",
                fontSize = 20.sp)
        }

    }
}