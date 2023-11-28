package com.example.quizheads.firebase.splash

import com.example.quizheads.MAIN_SCREEN
import com.example.quizheads.SIGN_IN_SCREEN
import com.example.quizheads.SPLASH_SCREEN
import com.example.quizheads.firebase.model.service.AccountService
import com.example.quizheads.firebase.QuizHeadsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : QuizHeadsViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(MAIN_SCREEN, SPLASH_SCREEN)
        else openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    }
}
