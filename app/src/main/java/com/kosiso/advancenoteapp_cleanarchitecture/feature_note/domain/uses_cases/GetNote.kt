package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases

import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.repository.NoteRepository

class GetNote (
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note?{
        return noteRepository.getNoteById(id)
    }
}