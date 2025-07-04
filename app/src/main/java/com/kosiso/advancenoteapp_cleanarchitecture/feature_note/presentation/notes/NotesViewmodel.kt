package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.presentation.notes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases.NotesUseCases
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.util.NoteOrder
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewmodel @Inject constructor(
    private val notesUseCases: NotesUseCases
): ViewModel() {

    val _noteState = mutableStateOf(NotesState())
    val noteState: State<NotesState> = _noteState

    var recentlyDeletedNote: Note? = null

    var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }


    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.Order -> {
                // checking if the note order and the order a user is changing it to is the same...
                //... and also if the order type (descending or ascending) is same.
                if(noteState.value.noteOrder::class == event.noteOrder::class &&
                    noteState.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }
                getNotes(event.noteOrder)
            }

            NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            NotesEvent.ToggleOrderSection -> {
                _noteState.value = noteState.value.copy(
                    isOrderSectionVisible = !noteState.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = notesUseCases.getAllNotes(noteOrder)
            .onEach {notes->
                _noteState.value = noteState.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
                Log.i("list of notes vm", "${notes}")
            }
            .launchIn(viewModelScope)
    }
}