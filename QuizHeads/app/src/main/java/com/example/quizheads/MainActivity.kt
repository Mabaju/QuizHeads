package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.component.MyAppBar
import com.example.quizheads.ui.theme.QuizHeadsTheme
import kotlinx.coroutines.launch
import com.example.quizheads.firebase.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.quizheads.firebase.User
import com.example.quizheads.QuizResultActivity


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser == null) {
            // Brugeren er ikke logget ind, start LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Afslutter MainActivity
            return
        }

        // Hent brugerdata
        val user = User.getInstance(firebaseUser.uid)
        user.fetchUserData {

            setContent {

                QuizHeadsTheme {
                    val scope = rememberCoroutineScope()
                    val drawerState = rememberDrawerState(DrawerValue.Closed)

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                Surface(
                                    modifier = Modifier.fillMaxSize(),
                                    color = Color.DarkGray
                                )
                                {
                                    Column {
                                        Text(
                                            "QuizHeads",
                                            fontSize = 50.sp, // Change font size
                                            color = Color.White, // Change text color
                                            modifier = Modifier.align(Alignment.CenterHorizontally) // Center the text horizontally
                                        )

                                        Button(modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                val intent =
                                                    Intent(
                                                        this@MainActivity,
                                                        QuizActivity::class.java
                                                    )
                                                startActivity(intent)
                                            }
                                        ) {
                                            Text(
                                                "Quiz",
                                                fontSize = 20.sp, // Change font size
                                                color = Color.Black // Change text color
                                            )
                                        }

                                        Button(modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                val intent = Intent(
                                                    this@MainActivity,
                                                    QuizResultActivity::class.java
                                                )
                                                startActivity(intent)
                                            }
                                        ) {
                                            Text(
                                                "Score",
                                                fontSize = 20.sp, // Change font size
                                                color = Color.Black // Change text color
                                            )
                                        }

                                        Button(modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                val intent =
                                                    Intent(
                                                        this@MainActivity,
                                                        FriendsActivity::class.java
                                                    )
                                                startActivity(intent)
                                            }) {
                                            Text(
                                                "Friends",
                                                fontSize = 20.sp, // Change font size
                                                color = Color.Black // Change text color
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(16.dp)) // Tilføj lidt plads mellem knapperne

                                        Button(modifier = Modifier.fillMaxWidth(),
                                            onClick = {
                                                FirebaseAuth.getInstance()
                                                    .signOut() // Log brugeren ud
                                                val intent = Intent(
                                                    this@MainActivity,
                                                    LoginActivity::class.java
                                                )
                                                startActivity(intent)
                                                finish() // Afslutter MainActivity
                                            }
                                        ) {
                                            Text(
                                                "Log ud",
                                                fontSize = 20.sp, // Change font size
                                                color = Color.Black // Change text color
                                            )
                                        }

                                    }
                                }
                            }
                        },
                        content = {
                            Scaffold(
                                topBar =
                                {
                                    MyAppBar(title = "", onMenu = {
                                        if (drawerState.isOpen)
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        else
                                            scope.launch {
                                                drawerState.open()
                                            }
                                    })
                                },
                                content =
                                { padding ->
                                    Column(modifier = Modifier.padding(padding)) {

                                        Spacer(modifier = Modifier.height(40.dp))

                                        Text(
                                            "QuizHeads\n\nWelcome ${user.firstName} ${user.lastName}",
                                            fontSize = 30.sp, // Change font size
                                            color = Color.Black, // Change text color
                                            textAlign = TextAlign.Center, // Center the text horizontally
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(padding),
                                            contentAlignment = Alignment.Center
                                        ) {
                                        }
                                    }
                                })
                        }
                    )
                }
            }
        }
    }
}



