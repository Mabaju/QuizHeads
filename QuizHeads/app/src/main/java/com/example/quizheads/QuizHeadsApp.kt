package com.example.quizheads

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.notes.app.screens.note.NoteScreen
import com.notes.app.screens.notes_list.NotesListScreen

import com.example.quizheads.firebase.signin.SignInScreen
import com.example.quizheads.firebase.signup.SignUpScreen
import com.example.quizheads.firebase.splash.SplashScreen
import com.example.quizheads.ui.theme.QuizHeadsTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun QuizHeadsApp() {
    QuizHeadsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    QuizHeadsGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        QuizHeadsAppState(navController)
    }

fun NavGraphBuilder.notesGraph(appState: NotesAppState) {
    composable(NOTES_LIST_SCREEN) {
        QuizHeadsListScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(
        route = "$NOTE_SCREEN$NOTE_ID_ARG",
        arguments = listOf(navArgument(NOTE_ID) { defaultValue = NOTE_DEFAULT_ID })
    ) {
        NoteScreen(
            noteId = it.arguments?.getString(NOTE_ID) ?: NOTE_DEFAULT_ID,
            popUpScreen = { appState.popUp() },
            restartApp = { route -> appState.clearAndNavigate(route) }
        )
    }
//s
    composable(SIGN_IN_SCREEN) {
        SignInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
}
