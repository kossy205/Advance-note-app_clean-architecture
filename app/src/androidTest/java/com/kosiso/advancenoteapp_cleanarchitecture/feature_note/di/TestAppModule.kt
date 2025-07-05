package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kosiso.advancenoteapp_cleanarchitecture.common.Constants
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.data_source.NoteDao
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.data_source.NoteDatabase
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.data.repository.NoteRepositoryImpl
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.repository.NoteRepository
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases.DeleteNote
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases.GetAllNotes
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases.AddNote
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases.GetNote
import com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.uses_cases.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object TestAppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NoteDatabase{
        // "inMemoryDatabaseBuilder" for testing
        return Room.inMemoryDatabaseBuilder(
            app,
            NoteDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase) = db.noteDao

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository{
        return NoteRepositoryImpl(noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository): NotesUseCases{
        return NotesUseCases(
            getAllNotes = GetAllNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )
    }
}