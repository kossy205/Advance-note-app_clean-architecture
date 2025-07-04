package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases

data class NotesUseCases (
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val deleteNote: DeleteNote
)