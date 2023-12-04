package com.example.quizheads.firebase

class User(val userId: String) {
    // Du kan tilføje flere brugerrelaterede egenskaber her
    // For eksempel, brugerens navn, e-mail osv.

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
