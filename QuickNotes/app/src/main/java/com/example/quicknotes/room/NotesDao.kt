package com.example.quicknotes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quicknotes.Utility.DATE_TIME

@Dao
interface NotesDao {
    @Query("SELECT * FROM NOTES_TABLE ORDER BY $DATE_TIME DESC")
    suspend fun getAllNotes(): List<NotesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notesEntity: NotesEntity)

    @Query("DELETE FROM NOTES_TABLE WHERE id = :id")
    suspend fun deleteNote(id: Int)

    @Update
    suspend fun updateNote(notesEntity: NotesEntity)
}