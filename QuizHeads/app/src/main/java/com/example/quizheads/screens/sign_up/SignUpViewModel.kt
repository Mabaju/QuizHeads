package com.example.quizheads.screens.sign_up

import com.example.quizheads.NOTES_LIST_SCREEN
import com.example.quizheads.SIGN_UP_SCREEN
import com.example.quizheads.model.service.AccountService
import com.example.quizheads.screens.NotesAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
) : NotesAppViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if (password.value != confirmPassword.value) {
                throw Exception("Passwords do not match")
            }

            accountService.signUp(email.value, password.value)
            openAndPopUp(NOTES_LIST_SCREEN, SIGN_UP_SCREEN)
        }
    }
}