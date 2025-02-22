package com.example.quicknotes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quicknotes.Utility.DATE_TIME
import com.example.quicknotes.Utility.DESCRIPTION
import com.example.quicknotes.Utility.NOTES_TABLE
import com.example.quicknotes.Utility.TITLE

@Entity(tableName = NOTES_TABLE)
data class NotesEntity (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = DESCRIPTION) val description: String,
    @ColumnInfo(name = DATE_TIME) val dateTime: Long
)