package com.example.quizheads.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.quizheads.person_api.Person

@Composable
fun FriendsList(fetchFriends:()->List<Person>, onClick: (id:String)->Unit){
    val friends = remember{mutableStateOf(emptyList<Person>())}
    LaunchedEffect(key1 = Unit) {
        friends.value = fetchFriends()
    }

    Column {
        friends.value.map{
            FriendsOverview(it, onClick = onClick)
        }
    }
}