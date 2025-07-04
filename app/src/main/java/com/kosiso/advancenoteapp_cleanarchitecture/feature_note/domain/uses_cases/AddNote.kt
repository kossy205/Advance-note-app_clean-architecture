package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases

import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.InvalidNoteException
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("Note title cannot be blank")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("Note content cannot be blank")
        }
        noteRepository.insertNote(note)
    }
}