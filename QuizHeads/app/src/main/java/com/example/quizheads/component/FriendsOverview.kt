package com.example.quizheads.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.quizheads.person_api.Person

@Composable
fun FriendsOverview(friend: Person, onClick: (id: String) -> Unit) {
    Column {
        Text(text = friend.name, modifier = Modifier.clickable { onClick(friend.id) })
    }
}