package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import com.example.quizheads.component.MyAppBar
import com.example.quizheads.ui.theme.QuizHeadsTheme
import kotlinx.coroutines.launch
import com.example.quizheads.firebase.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.quizheads.firebase.User
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tjek om brugeren er logget ind
        if (FirebaseAuth.getInstance().currentUser == null) {
            // Brugeren er ikke logget ind, start LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Afslutter MainActivity, da brugeren ikke er logget ind
            return
        }

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
                                        textAlign = TextAlign.Center // Center the text horizontally
                                    )

                                    Button(onClick = {
                                        val intent = Intent(this@MainActivity, QuizActivity::class.java)
                                        startActivity(intent)
                                    }
                                    ) {
                                        Text("Quiz",
                                            fontSize = 20.sp, // Change font size
                                            color = Color.Black) // Change text color
                                    }


                                    Button(onClick = {
                                        val intent = Intent(this@MainActivity, ScoreActivity::class.java)
                                        startActivity(intent)
                                    }
                                    ) {
                                        Text("Score",
                                            fontSize = 20.sp, // Change font size
                                            color = Color.Black) // Change text color
                                    }


                                    Button(onClick = {
                                        val intent = Intent(this@MainActivity, FriendsActivity::class.java)
                                        startActivity(intent)
                                    }) {
                                        Text("Friends",
                                            fontSize = 20.sp, // Change font size
                                            color = Color.Black) // Change text color

                                    }


                                    Button(onClick = {
                                        // Log brugeren ud
                                        FirebaseAuth.getInstance().signOut()

                                        // Ryd User instansen
                                        User.clear()

                                        // Start LoginActivity
                                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                                        startActivity(intent)
                                        finish() // Afslutter MainActivity
                                    }) {
                                        Text("Log ud",
                                            fontSize = 20.sp, // Change font size
                                            color = Color.Black) // Change text color
                                    }

                                }
                            }
                        }
                    },
                    content = {
                        Scaffold(
                            topBar =
                            {
                                MyAppBar(title = "menu", onMenu = {
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
                                    Text(
                                        "QuizHeads",
                                        fontSize = 50.sp, // Change font size
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



