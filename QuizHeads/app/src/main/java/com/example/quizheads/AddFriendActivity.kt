package com.example.quizheads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizheads.firebase.User
import com.example.quizheads.ui.theme.QuizHeadsTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddFriendActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizHeadsTheme {
                AddFriendScreen()
            }
        }
    }
}

@Composable
fun AddFriendScreen() {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            decorationBox = { innerTextField ->
                if (email.isEmpty()) {
                    Text("Enter friend's email")
                }
                innerTextField()
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                addFriend(email) { resultMessage ->
                    message = resultMessage
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Friend")
        }

        Text(message, modifier = Modifier.padding(top = 8.dp))
    }
}


private fun addFriend(email: String, onResult: (String) -> Unit) {
    val currentUser = User.getInstance(FirebaseAuth.getInstance().currentUser!!.uid)
    FirebaseFirestore.getInstance().collection("users")
        .whereEqualTo("email", email)
        .get()
        .addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                onResult("Email is not an user")
            } else {
                var alreadyFriend = false
                for (document in documents) {
                    if (currentUser.friendsList.contains(document.id)) {
                        alreadyFriend = true
                        break
                    }
                }

                if (alreadyFriend) {
                    onResult("Already on friend list")
                } else {
                    for (document in documents) {
                        currentUser.friendsList.add(document.id)
                        FirebaseFirestore.getInstance().collection("users")
                            .document(currentUser.userId)
                            .update("friendsList", currentUser.friendsList)
                    }
                    onResult("User added to friend list")
                }
            }
        }
}

