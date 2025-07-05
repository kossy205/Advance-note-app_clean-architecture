package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases

import com.google.common.truth.Truth.assertThat
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.repository.FakeRepository
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.InvalidNoteException
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.util.NoteOrder
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test


class AddNoteTest {
    lateinit var addNote: AddNote
    lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
        addNote = AddNote(fakeRepository)

    }

    @Test
    fun `title is blank should throw exception`() = runBlocking {
        val note = Note(
            id = 2,
            title = "",
            content = "content",
            timeStamp = 0L,
            color = 222222
        )

        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }

        assertThat(exception).hasMessageThat().contains("Note title cannot be blank")
    }

    @Test
    fun `content is blank should throw exception`() = runBlocking {
        val note = Note(
            id = 2,
            title = "title",
            content = "",
            timeStamp = 0L,
            color = 222222
        )

        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }

        assertThat(exception).hasMessageThat().contains("Note content cannot be blank")
    }

    @Test
    fun `valid note added shouldn't throw exception`() = runBlocking {
        val note = Note(
            id = 2,
            title = "title",
            content = "content",
            timeStamp = 0L,
            color = 222222
        )

        val addNote = addNote(note)
    }


}