package com.example.taipeizoomdemo.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taipeizoomdemo.model.repository.BaseRepository
import com.example.taipeizoomdemo.model.repository.MainRepository
import com.example.taipeizoomdemo.view.main.MainViewModel

class ViewModelFactory(private val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            // Activity
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository as MainRepository) as T
            }

            // All Fragments

            else -> throw IllegalArgumentException("Unknown ViewModel Class.")
        }
    }
}