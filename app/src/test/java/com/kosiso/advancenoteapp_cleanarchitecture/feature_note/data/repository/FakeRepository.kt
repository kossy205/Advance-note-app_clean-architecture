package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.repository

import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: NoteRepository {

    val notes = mutableListOf<Note>()

    override suspend fun insertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return flow { emit(notes) }
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }
}