package com.example.taipeizoomdemo.view.house_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.taipeizoomdemo.databinding.FragmentHouseInfoBinding
import com.example.taipeizoomdemo.model.network.Resource
import com.example.taipeizoomdemo.model.repository.HouseInfoRepository
import com.example.taipeizoomdemo.view.base.BaseFragment
import com.example.taipeizoomdemo.view.base.handleApiError

/**
 * A simple [Fragment] subclass.
 */
class HouseInfoFragment : BaseFragment<HouseInfoViewModel, FragmentHouseInfoBinding, HouseInfoRepository>() {

    private val args: HouseInfoFragmentArgs by navArgs()
    private val housePojo by lazy { args.HousePojo }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHouseListData()
        setObserver()
    }

    private fun getHouseListData() {
        viewModel.getZoomPlantList()
    }

    private fun setObserver() {
        viewModel.getZoomPlantListResponse.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    Log.d("JLin", "size: ${it.value.result.results.size}")

                    activity.dismissProgressBar()
                }

                is Resource.Failure -> handleApiError(it)

                is Resource.Loading -> activity.showProgressBar(true)
            }
        }
    }

    override fun getViewModel() = HouseInfoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHouseInfoBinding = FragmentHouseInfoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = HouseInfoRepository()
}