package com.example.quizheads

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizheads.component.QuizDetails
import com.example.quizheads.person_api.Api
import com.example.quizheads.ui.theme.QuizHeadsTheme
import com.google.firebase.auth.FirebaseAuth
import com.example.quizheads.firebase.User
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.*
import java.io.IOException
import org.json.JSONObject
import androidx.compose.ui.text.font.FontWeight

class QuizDetailsActivity : ComponentActivity() {
    val api = Api()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizHeadsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var searchResults by remember { mutableStateOf(listOf<WikipediaResult>()) }
                    var currentIndex by remember { mutableStateOf(0) }
                    var previousIndex by remember { mutableStateOf(-1) }

                    if (currentIndex != previousIndex) {
                        searchResults = listOf() // Nulstil søgeresultaterne
                        previousIndex = currentIndex
                    }

                    LazyColumn {
                        item {
                            Column {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Button(modifier = Modifier.width(195.dp), onClick = {
                                        val intent =
                                            Intent(this@QuizDetailsActivity, QuizActivity::class.java)

                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                                        startActivity(intent)
                                    }) {
                                        Text(
                                            "Go back", fontSize = 20.sp,
                                            color = Color.Black
                                        )
                                    }

                                    Button(onClick = {
                                        val intent =
                                            Intent(this@QuizDetailsActivity, MainActivity::class.java)

                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

                                        startActivity(intent)
                                    }) {
                                        Text(
                                            "Back to Frontpage", fontSize = 20.sp,
                                            color = Color.Black
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(20.dp))

                                QuizDetails(
                                    fetchQuiz = api.getQuizById(intent.getStringExtra("id") ?: ""),
                                    onLastClick = { correctAnswersCount ->
                                        val user = User.getInstance(FirebaseAuth.getInstance().currentUser!!.uid)
                                        val correctAnswersCountInt = correctAnswersCount.toIntOrNull() ?: 0
                                        user.updateScore(correctAnswersCountInt)

                                        val intent = Intent(this@QuizDetailsActivity, QuizResultActivity::class.java)
                                        startActivity(intent)
                                    },
                                    onSearchClick = { question ->
                                        searchWikipedia(question) { results ->
                                            searchResults = results
                                        }
                                    },
                                    currentIndex = currentIndex,
                                    onIndexChanged = { newIndex ->
                                        if (newIndex != currentIndex) {
                                            currentIndex = newIndex
                                            searchResults = listOf() // Nulstil søgeresultaterne
                                        }
                                    }
                                )
                            }
                        }

                        item {
                            if (searchResults.isNotEmpty()) {
                                Text(
                                    "Wikipedia search:",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }

                        items(searchResults.size) { index ->
                            val result = searchResults[index]
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = result.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text(text = result.snippet, fontSize = 16.sp)
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun searchWikipedia(question: String, onResult: (List<WikipediaResult>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()
            val url = "https://da.wikipedia.org/w/api.php?action=query&list=search&srsearch=${question}&format=json"
            val request = Request.Builder().url(url).build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val responseData = response.body?.string()
                val results = parseWikipediaResponse(responseData)
                withContext(Dispatchers.Main) {
                    onResult(results)
                }
            }
        }
    }

    private fun parseWikipediaResponse(responseData: String?): List<WikipediaResult> {
        val results = mutableListOf<WikipediaResult>()
        responseData?.let {
            val jsonObject = JSONObject(it)
            val query = jsonObject.getJSONObject("query")
            val search = query.getJSONArray("search")
            for (i in 0 until search.length().coerceAtMost(3)) {
                val item = search.getJSONObject(i)
                val title = item.getString("title")
                val snippet = item.getString("snippet").replace(Regex("<[^>]*>"), "") // Remove HTML tags
                results.add(WikipediaResult(title, snippet))
            }
        }
        return results
    }
}

data class WikipediaResult(val title: String, val snippet: String)
