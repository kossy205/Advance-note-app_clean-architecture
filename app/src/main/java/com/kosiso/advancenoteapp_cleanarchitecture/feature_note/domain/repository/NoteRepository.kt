package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.repository

import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun getNoteById(id: Int): Note?

    fun getAllNotes(): Flow<List<Note>>

    suspend fun deleteNote(note: Note)
}