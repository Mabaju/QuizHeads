package com.example.quizheads.firebase

import com.google.firebase.firestore.FirebaseFirestore

class User(
    val userId: String,
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var quizzesTaken: Int = 0,
    var totalScore: Int = 0
) {
    fun updateScore(newScore: Int) {
        totalScore += newScore
        quizzesTaken++
        val userMap = mapOf(
            "quizzesTaken" to quizzesTaken,
            "totalScore" to totalScore
        )
        FirebaseFirestore.getInstance().collection("users").document(userId).update(userMap)
    }

    companion object {
        private var instance: User? = null
        private val db = FirebaseFirestore.getInstance()

        fun getInstance(userId: String): User {
            if (instance == null) {
                instance = User(userId)
                db.collection("users").document(userId).get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            instance?.apply {
                                email = document.getString("email") ?: ""
                                firstName = document.getString("firstName") ?: ""
                                lastName = document.getString("lastName") ?: ""
                                quizzesTaken = document.getLong("quizzesTaken")?.toInt() ?: 0
                                totalScore = document.getLong("totalScore")?.toInt() ?: 0
                            }
                        }
                    }
                    .addOnFailureListener {
                        // Håndter fejl
                    }
            }
            return instance!!
        }

        fun updateUserData() {
            instance?.let { user ->
                val userData = hashMapOf(
                    "email" to user.email,
                    "firstName" to user.firstName,
                    "lastName" to user.lastName,
                    "quizzesTaken" to user.quizzesTaken,
                    "totalScore" to user.totalScore
                )
                db.collection("users").document(user.userId).set(userData)
                    .addOnSuccessListener {
                        // Data opdateret
                    }
                    .addOnFailureListener {
                        // Håndter fejl
                    }
            }
        }

        fun clear() {
            instance = null
        }
    }
}
