package com.example.quizheads.person_api


data class Quiz(
    val id: String,
    val name: String,
    val questions: MutableList<QuestionAnswers> = mutableListOf<QuestionAnswers>()
)
