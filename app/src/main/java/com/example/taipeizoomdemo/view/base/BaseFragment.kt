package com.example.taipeizoomdemo.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.taipeizoomdemo.model.repository.BaseRepository
import com.example.taipeizoomdemo.view.main.MainActivity

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding, BR : BaseRepository>: Fragment() {

    protected var TAG = javaClass.simpleName
    protected lateinit var viewModel: VM
    protected lateinit var binding: VB

    protected lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is MainActivity) {
            activity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)

        val viewModelFactory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())

        return binding.root
    }

    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun getFragmentRepository(): BR
}