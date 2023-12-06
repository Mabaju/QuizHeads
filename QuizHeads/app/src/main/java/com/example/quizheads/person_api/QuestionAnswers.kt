package com.example.quizheads.person_api

data class QuestionAnswers(
    val question: String,
    val correctAnswer: Int,
    val answerPosibilities: MutableList<String> = mutableListOf<String>()
)


