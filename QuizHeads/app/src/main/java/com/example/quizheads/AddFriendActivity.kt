package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.firebase.User
import com.example.quizheads.ui.theme.QuizHeadsTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddFriendActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizHeadsTheme {
                AddFriendScreen(onGoBack = {
                    finish() // Afslutter denne aktivitet og vender tilbage til den forrige
                })
            }
        }
    }
}

@Composable
fun AddFriendScreen(onGoBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth()
    ) {
        // Tilbage knap
        Button(onClick = onGoBack) {
            Text("Go back", fontSize = 20.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(40.dp))

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            decorationBox = { innerTextField ->
                if (email.isEmpty()) {
                    Text("Enter friend's email here")
                }
                innerTextField()
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black) // Tilføjer en sort streg under tekstfeltet
                .padding(8.dp) // Tilføjer lidt polstring omkring tekstfeltet
        )

        Button(
            onClick = {
                addFriend(email) { resultMessage ->
                    message = resultMessage
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Friend", fontSize = 20.sp, color = Color.Black)
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

