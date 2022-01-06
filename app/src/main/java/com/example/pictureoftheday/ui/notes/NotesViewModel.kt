package com.example.pictureoftheday.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pictureoftheday.model.NoteSmall
import com.example.pictureoftheday.repository.DataSource
import com.example.pictureoftheday.util.DateHelper
import com.example.pictureoftheday.util.ListItem
import java.util.*

class NotesViewModel(private val dataSource: DataSource) : ViewModel() {

    val notesLiveData = dataSource.getListItem()

    fun insertNote(noteTitle: String?, noteDescription: String?) {

        if (noteTitle == null || noteDescription == null) {
            return
        }

        notesLiveData.value?.let { list ->
            val note = list.firstOrNull { it is NoteSmall } as NoteSmall
            val id = note.id
            val newNote =
                NoteSmall(id + 1, noteTitle, noteDescription, DateHelper().dayLong(Date().time))
            dataSource.addItem(newNote)

        }
    }

    fun removeNote(listItem: ListItem) {
        dataSource.removeItem(listItem)
    }
}

class NotesViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(
                dataSource = DataSource.getDataSource()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
