package com.example.quizheads.model.service

import com.example.quizheads.model.Note
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val notes: Flow<List<Note>>
    suspend fun createNote(note: Note)
    suspend fun readNote(noteId: String): Note?
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(noteId: String)
}