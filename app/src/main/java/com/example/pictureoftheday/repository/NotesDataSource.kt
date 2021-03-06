package com.example.pictureoftheday.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pictureoftheday.R
import com.example.pictureoftheday.model.ListItem
import com.example.pictureoftheday.model.NoteBig
import com.example.pictureoftheday.model.NoteSmall

class DataSource(val resources: Resources) {
    private val initialListItem = dataList(resources)
    private val listItemLiveData = MutableLiveData(initialListItem)

    fun addItem(listItem: ListItem) {
        val currentList = listItemLiveData.value
        if (currentList == null) {
            listItemLiveData.postValue(listOf(listItem))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, listItem)
            listItemLiveData.postValue(updatedList)
        }
    }

    fun removeItem(listItem: ListItem) {
        listItemLiveData.value?.let { list ->
            list.toMutableList().also {
                it.remove(listItem)
                listItemLiveData.postValue(it)
            }
        }
    }

    fun getListItem(): LiveData<List<ListItem>> {
        return listItemLiveData
    }

    fun checkBoxFavoriteClick(listItem: ListItem) {
        listItemLiveData.value?.let { currentList ->
            when (listItem) {
                is NoteBig -> {
                    for (item in currentList) {
                        if (item is NoteBig && item.id == listItem.id) {
                            item.isFavorite = !item.isFavorite
                        }
                    }
                }

                is NoteSmall -> {
                    for (item in currentList) {
                        if (item is NoteSmall && item.id == listItem.id) {
                            item.isFavorite = !item.isFavorite
                        }
                    }
                }
            }

            val updatedList = currentList.toMutableList()

            updatedList.sortWith(compareBy {
                when (it) {
                    is NoteSmall -> {
                        !it.isFavorite
                    }
                    is NoteBig -> {
                        !it.isFavorite
                    }
                    else -> {
                        false
                    }
                }
            })
            listItemLiveData.postValue(updatedList)
        }
    }

    fun getRandomImage(): Int {
        return when ((0..1).random()) {
            0 -> R.drawable.full_moon
            1 -> R.drawable.space_astronaut
            else -> R.drawable.space
        }
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}