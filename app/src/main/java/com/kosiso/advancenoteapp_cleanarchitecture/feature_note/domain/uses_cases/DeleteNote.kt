package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases

import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.repository.NoteRepository

class DeleteNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        noteRepository.deleteNote(note)
    }
}