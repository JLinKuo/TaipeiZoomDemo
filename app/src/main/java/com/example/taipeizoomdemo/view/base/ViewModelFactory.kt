package com.example.taipeizoomdemo.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taipeizoomdemo.view.house_info.HouseInfoViewModel
import com.example.taipeizoomdemo.view.house_list.HouseListViewModel
import com.example.taipeizoomdemo.view.main.MainViewModel
import com.example.taipeizoomdemo.view.plant_info.PlantInfoViewModel

class ViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            // Activity
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
            }

            // All Fragments
            modelClass.isAssignableFrom(HouseListViewModel::class.java) -> {
                HouseListViewModel() as T
            }
            modelClass.isAssignableFrom(HouseInfoViewModel::class.java) -> {
                HouseInfoViewModel() as T
            }
            modelClass.isAssignableFrom(PlantInfoViewModel::class.java) -> {
                PlantInfoViewModel() as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class.")
        }
    }
}