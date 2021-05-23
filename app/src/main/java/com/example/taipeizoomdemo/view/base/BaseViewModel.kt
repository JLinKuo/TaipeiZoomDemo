package com.example.taipeizoomdemo.view.base

import androidx.lifecycle.ViewModel
import com.example.taipeizoomdemo.model.repository.BaseRepository

abstract class BaseViewModel(private val repository: BaseRepository): ViewModel() {
    protected var TAG = javaClass.simpleName
}