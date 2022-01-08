package com.example.pictureoftheday.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pictureoftheday.model.ListItem

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