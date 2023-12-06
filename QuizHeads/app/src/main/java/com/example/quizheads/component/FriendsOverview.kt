package com.example.quizheads.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.quizheads.firebase.User

@Composable
fun FriendsOverview(friend: User, onClick: (id: String) -> Unit) {
    Column {
        Text(text = "${friend.firstName} ${friend.lastName}", modifier = Modifier.clickable { onClick(friend.userId) })
    }
}
