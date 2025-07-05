package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases

import android.util.Log
import com.google.common.truth.Truth.assertThat
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.repository.FakeRepository
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.util.NoteOrder
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetAllNotesTest {
    lateinit var getAllNotes: GetAllNotes
    lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp(){
        fakeRepository = FakeRepository()
        getAllNotes = GetAllNotes(fakeRepository)

        // We are trying to test getting notes, so we have to simulate getting it.
        // Since it has to do with ordering, we would have to test that and one ...
        // ... sure way is to insert notes in such a way as that would be possible.
        // Using A to Z will be a sure better way.
        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, char ->
            notesToInsert.add(
                Note(
                    title = char.toString(),
                    content = char.toString(),
                    timeStamp = index.toLong(),
                    color = index
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach { fakeRepository.insertNote(it) }
        }
    }

    @Test
    fun `order notes by title ascending, correct order`() = runBlocking{
        val notes = getAllNotes(NoteOrder.Title(OrderType.Ascending)).first()

        println("Notes size: ${notes.size}, titles: ${notes.map { it.title }}")

        for(i in 0..notes.size-2){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }

    @Test
    fun `order notes by title descending, correct order`() = runBlocking{
        val notes = getAllNotes(NoteOrder.Title(OrderType.Descending)).first()
        for(i in 0..notes.size-2){
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }

    @Test
    fun `order notes by timestamp ascending, correct order`() = runBlocking{
        val notes = getAllNotes(NoteOrder.Date(OrderType.Ascending)).first()
        for(i in 0..notes.size-2){
            assertThat(notes[i].timeStamp).isLessThan(notes[i+1].timeStamp)
        }
    }

    @Test
    fun `order notes by timestamp descending, correct order`() = runBlocking{
        val notes = getAllNotes(NoteOrder.Date(OrderType.Descending)).first()
        for(i in 0..notes.size-2){
            assertThat(notes[i].timeStamp).isGreaterThan(notes[i+1].timeStamp)
        }
    }

    @Test
    fun `Order notes by color ascending, correct order`() = runBlocking {
        val notes = getAllNotes(NoteOrder.Color(OrderType.Ascending)).first()

        for(i in 0..notes.size - 2) {
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }

    @Test
    fun `Order notes by color descending, correct order`() = runBlocking {
        val notes = getAllNotes(NoteOrder.Color(OrderType.Descending)).first()

        for(i in 0..notes.size - 2) {
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }
}