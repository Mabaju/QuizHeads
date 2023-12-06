package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.component.FriendsList
import com.example.quizheads.firebase.User
import com.example.quizheads.ui.theme.QuizHeadsTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class FriendsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = User.getInstance(FirebaseAuth.getInstance().currentUser!!.uid)
        val friendsDetails = mutableStateListOf<User>()

        setContent {
            QuizHeadsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val coroutineScope = rememberCoroutineScope()

                    LaunchedEffect(key1 = Unit) {
                        coroutineScope.launch {
                            currentUser.fetchFriendsList { friendsList ->
                                friendsList.forEach { friendId ->
                                    FirebaseFirestore.getInstance().collection("users").document(friendId).get()
                                        .addOnSuccessListener { document ->
                                            if (document != null) {
                                                val friend = User(
                                                    userId = friendId,
                                                    email = document.getString("email") ?: "",
                                                    firstName = document.getString("firstName") ?: "",
                                                    lastName = document.getString("lastName") ?: "",
                                                    quizzesTaken = document.getLong("quizzesTaken")?.toInt() ?: 0,
                                                    totalScore = document.getLong("totalScore")?.toInt() ?: 0
                                                )
                                                friendsDetails.add(friend)
                                            }
                                        }
                                        .addOnFailureListener {
                                            // Håndter fejl
                                        }
                                }
                            }
                        }
                    }

                    Column {
                        Button(onClick = {
                            val intent = Intent(this@FriendsActivity, MainActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Go back", fontSize = 20.sp, color = Color.Black)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(onClick = {
                            val intent = Intent(this@FriendsActivity, AddFriendActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Add Friends", fontSize = 20.sp, color = Color.Black)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("Friends", fontSize = 30.sp, color = Color.Black)

                        FriendsList(friends = friendsDetails, onClick = { friendId ->
                            val intent = Intent(this@FriendsActivity, FriendsDetailsActivity::class.java)
                            intent.putExtra("id", friendId)
                            startActivity(intent)
                        })
                    }
                }
            }
        }
    }
}
