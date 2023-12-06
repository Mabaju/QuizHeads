package com.example.quizheads.firebase

import com.google.firebase.firestore.FirebaseFirestore

class User(
    val userId: String,
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var quizzesTaken: Int = 0,
    var totalScore: Int = 0,
    var friendsList: MutableList<String> = mutableListOf()
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

    fun fetchFriendsList(onComplete: (List<String>) -> Unit) {
        FirebaseFirestore.getInstance().collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                val friendsList = document["friendsList"] as? List<String> ?: listOf()
                this.friendsList = friendsList.toMutableList()
                onComplete(friendsList)
            }
    }

    fun fetchUserData(onComplete: () -> Unit) {
        FirebaseFirestore.getInstance().collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    this.email = document.getString("email") ?: ""
                    this.firstName = document.getString("firstName") ?: ""
                    this.lastName = document.getString("lastName") ?: ""
                    this.quizzesTaken = document.getLong("quizzesTaken")?.toInt() ?: 0
                    this.totalScore = document.getLong("totalScore")?.toInt() ?: 0
                    this.friendsList = (document["friendsList"] as? List<String>)?.toMutableList()
                        ?: mutableListOf()
                    onComplete()
                }
            }
            .addOnFailureListener {
                // Håndter fejl
            }
    }

    fun fetchFriendData(friendId: String, onComplete: (User?) -> Unit) {
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
                    onComplete(friend)
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener {
                // Håndter fejl
                onComplete(null)
            }
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
