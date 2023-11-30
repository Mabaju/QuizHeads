package com.example.quizheads.screens.splash

import com.example.quizheads.NOTES_LIST_SCREEN
import com.example.quizheads.SIGN_IN_SCREEN
import com.example.quizheads.SPLASH_SCREEN
import com.example.quizheads.model.service.AccountService
import com.example.quizheads.screens.NotesAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val accountService: AccountService
) : NotesAppViewModel() {

  fun onAppStart(openAndPopUp: (String, String) -> Unit) {
    if (accountService.hasUser()) openAndPopUp(NOTES_LIST_SCREEN, SPLASH_SCREEN)
    else openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
  }
}
