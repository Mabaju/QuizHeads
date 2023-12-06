package com.example.quizheads.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.quizheads.firebase.User

@Composable
fun FriendsList(friends: List<User>, onClick: (id: String) -> Unit) {
    Column {
        friends.map { friend ->
            FriendsOverview(friend, onClick = onClick)
        }
    }
}


