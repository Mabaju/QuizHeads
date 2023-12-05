package com.example.quizheads.firebase

class User(
    val userId: String,
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var quizzesTaken: Int = 0,
    var totalScore: Int = 0
) {

    companion object {
        private var instance: User? = null

        fun getInstance(userId: String): User {
            if (instance == null) {
                instance = User(userId)
            }
            return instance!!
        }

        fun clear() {
            instance = null
        }
    }
}
