package com.example.pictureoftheday.ui.notes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pictureoftheday.model.ListItem
import com.example.pictureoftheday.model.NoteBig
import com.example.pictureoftheday.model.NoteSmall
import com.example.pictureoftheday.repository.DataSource
import com.example.pictureoftheday.util.DateHelper
import java.util.*

class NotesViewModel(private val dataSource: DataSource) : ViewModel() {

    val notesLiveData = dataSource.getListItem()

    fun insertNote(noteTitle: String?, noteDescription: String?) {

        if (noteTitle == null || noteDescription == null) {
            return
        }

        notesLiveData.value?.let { list ->

            val id = when (val noteFirst = list.first()) {
                is NoteBig -> noteFirst.id
                is NoteSmall -> noteFirst.id
                else -> -1
            }

            val note = when ((0..1).random()) {
                0 -> NoteSmall(
                    id + 1,
                    noteTitle,
                    noteDescription,
                    DateHelper().dayLong(Date().time)
                )

                else -> {
                    val image = dataSource.getRandomImage()
                    NoteBig(
                        id + 1,
                        image,
                        noteTitle,
                        noteDescription,
                        "Note with image...",
                        DateHelper().dayLong(Date().time)
                    )
                }
            }
            dataSource.addItem(note)
        }
    }

    fun removeNote(listItem: ListItem) {
        dataSource.removeItem(listItem)
    }

    fun onCheckBoxFavoriteClick(listItem: ListItem) {
        dataSource.checkBoxFavoriteClick(listItem)
    }
}

class NotesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
