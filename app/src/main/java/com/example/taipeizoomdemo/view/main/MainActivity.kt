package com.example.taipeizoomdemo.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taipeizoomdemo.R
import com.example.taipeizoomdemo.model.repository.MainRepository
import com.example.taipeizoomdemo.view.base.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelFactory(MainRepository()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("JLin", "main view model: $viewModel")
    }
}