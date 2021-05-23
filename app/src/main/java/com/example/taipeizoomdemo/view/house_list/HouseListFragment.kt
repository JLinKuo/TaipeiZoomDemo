package com.example.taipeizoomdemo.view.house_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taipeizoomdemo.databinding.FragmentHouseListBinding
import com.example.taipeizoomdemo.model.repository.HouseListRepository
import com.example.taipeizoomdemo.view.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HouseListFragment : BaseFragment<HouseListViewModel, FragmentHouseListBinding, HouseListRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHouseListData()
        setObserver()
    }

    private fun getHouseListData() {
        viewModel.getZoomHouseList()
    }

    private fun setObserver() {
        viewModel.getZoomHouseListResponse.observe(viewLifecycleOwner) {
            Log.d("JLin", "$it")
        }
    }

    override fun getViewModel() = HouseListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHouseListBinding = FragmentHouseListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = HouseListRepository()
}