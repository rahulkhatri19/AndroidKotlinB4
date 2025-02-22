package com.example.quicknotes

import com.example.quicknotes.model.NotesModel
import com.example.quicknotes.room.NotesDao
import com.example.quicknotes.room.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class NotesRepo(private val notesDao: NotesDao) {

    val notesList = MutableStateFlow<List<NotesModel>>(listOf())

    fun getAllNotes() {
        GlobalScope.launch(Dispatchers.IO) {
            val list = arrayListOf<NotesModel>()
            notesDao.getAllNotes().forEach {
                list.add(
                    NotesModel(
                        id = it.id ?: -1,
                        notesTitle = it.title,
                        notesDescription = it.description,
                        dateTime = it.dateTime
                    )
                )
            }
            notesList.value = list
        }
    }

    fun saveNote(title: String, description: String) {
        val currentTime = Calendar.getInstance()
        GlobalScope.launch(Dispatchers.IO) {
            notesDao.insertNote(
                NotesEntity(
                    id = null,
                    title = title,
                    description = description,
                    dateTime = currentTime.timeInMillis
                )
            )
        }
    }

    fun updateNote(id: Int, title: String, description: String) {
        val currentTime = Calendar.getInstance()
        GlobalScope.launch(Dispatchers.IO) {
            notesDao.updateNote(
                NotesEntity(
                    id = id,
                    title = title,
                    description = description,
                    dateTime = currentTime.timeInMillis
                )
            )
        }
    }

    fun deleteNote(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            notesDao.deleteNote(id = id)
        }
    }
}