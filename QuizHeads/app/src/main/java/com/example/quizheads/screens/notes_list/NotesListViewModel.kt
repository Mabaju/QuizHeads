package com.example.quizheads.screens.notes_list

import com.example.quizheads.NOTE_DEFAULT_ID
import com.example.quizheads.NOTE_ID
import com.example.quizheads.NOTE_SCREEN
import com.example.quizheads.SPLASH_SCREEN
import com.example.quizheads.model.Note
import com.example.quizheads.model.service.AccountService
import com.example.quizheads.model.service.StorageService
import com.example.quizheads.screens.NotesAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val accountService: AccountService,
    storageService: StorageService
) : NotesAppViewModel() {
    val notes = storageService.notes

    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(SPLASH_SCREEN)
            }
        }
    }
    fun onAddClick(openScreen: (String) -> Unit) {
        openScreen("$NOTE_SCREEN?$NOTE_ID=$NOTE_DEFAULT_ID")
    }

    fun onNoteClick(openScreen: (String) -> Unit, note: Note) {
        openScreen("$NOTE_SCREEN?$NOTE_ID=${note.id}")
    }

    fun onSignOutClick() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onDeleteAccountClick() {
        launchCatching {
            accountService.deleteAccount()
        }
    }
}