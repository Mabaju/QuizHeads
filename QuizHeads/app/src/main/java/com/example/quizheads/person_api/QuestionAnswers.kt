package com.example.quizheads.person_api

data class QuestionAnswers(
    val question: String,
    val answer: Int,
    val answerPosibilities: MutableList<String> = mutableListOf<String>()
)


