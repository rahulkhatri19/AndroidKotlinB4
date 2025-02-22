package com.example.quicknotes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quicknotes.Utility.NOTES_DATABASE

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
public abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    NOTES_DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}