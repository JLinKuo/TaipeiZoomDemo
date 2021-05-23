package com.example.taipeizoomdemo.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taipeizoomdemo.model.repository.BaseRepository
import com.example.taipeizoomdemo.model.repository.HouseListRepository
import com.example.taipeizoomdemo.model.repository.MainRepository
import com.example.taipeizoomdemo.view.house_list.HouseListViewModel
import com.example.taipeizoomdemo.view.main.MainViewModel

class ViewModelFactory(private val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            // Activity
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository as MainRepository) as T
            }

            // All Fragments
            modelClass.isAssignableFrom(HouseListViewModel::class.java) -> {
                HouseListViewModel(repository as HouseListRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class.")
        }
    }
}