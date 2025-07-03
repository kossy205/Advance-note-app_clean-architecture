package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Delete
    suspend fun deleteNote(note: Note)
}