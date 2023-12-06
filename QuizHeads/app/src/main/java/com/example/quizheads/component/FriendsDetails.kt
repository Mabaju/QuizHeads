package com.example.quizheads.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.quizheads.firebase.User

@Composable
fun FriendsDetails(friend: User?) {
    Column {
        Text(
            text = "${friend?.firstName} ${friend?.lastName}",
            fontSize = 30.sp
        )
        Text(text = "Quizzes done: ${friend?.quizzesTaken}")
        Text(text = "Score: ${friend?.totalScore}")
    }
}
