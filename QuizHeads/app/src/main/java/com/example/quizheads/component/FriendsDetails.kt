package com.example.quizheads.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import com.example.quizheads.person_api.Person

@Composable
fun FriendsDetails(fetchFriend: () -> Person?) {
    val friend = remember { mutableStateOf<Person?>(null) }
    LaunchedEffect(key1 = Unit) {
        friend.value = fetchFriend()
    }

    Column {
        Text(
            text = friend.value?.name.toString(),
            fontSize = 30.sp
        )
        Text(text = "Antal quiz lavet: ??????????")
        Text(text = "Score: ????????")
    }
}