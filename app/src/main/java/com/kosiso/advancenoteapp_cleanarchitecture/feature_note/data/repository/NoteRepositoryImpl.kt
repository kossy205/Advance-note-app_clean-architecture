package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.repository

import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.data_source.NoteDao
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

}