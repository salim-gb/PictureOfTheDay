package com.example.pictureoftheday.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pictureoftheday.model.ListItem
import com.example.pictureoftheday.model.NoteBig
import com.example.pictureoftheday.model.NoteSmall

class DataSource {
    private val initialListItem = dataList()
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

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}