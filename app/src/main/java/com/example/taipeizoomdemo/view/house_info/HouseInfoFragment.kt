package com.example.taipeizoomdemo.view.house_info

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.taipeizoomdemo.R
import com.example.taipeizoomdemo.databinding.FragmentHouseInfoBinding
import com.example.taipeizoomdemo.model.network.Resource
import com.example.taipeizoomdemo.model.network.bean.PlantBean
import com.example.taipeizoomdemo.model.repository.HouseInfoRepository
import com.example.taipeizoomdemo.view.base.BaseFragment
import com.example.taipeizoomdemo.view.base.handleApiError
import com.example.taipeizoomdemo.view.pojo.HousePojo
import com.example.taipeizoomdemo.view.pojo.PlantPojo
import com.google.android.material.appbar.AppBarLayout

/**
 * A simple [Fragment] subclass.
 */
class HouseInfoFragment : BaseFragment<HouseInfoViewModel, FragmentHouseInfoBinding, HouseInfoRepository>() {

    private val args: HouseInfoFragmentArgs by navArgs()
    private val housePojo by lazy { args.HousePojo }

    private val listAdapter by lazy { HouseInfoListAdapter(housePojo) }
    private var totalItems = 0
    private var currItemAmounts = 0      // 一開始所累積的Item數量是0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHouseListData()
        setView()
        setObserver()
    }

    private fun getHouseListData() {
        viewModel.getZoomPlantList()
    }

    private fun setView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.adapter = listAdapter
        binding.recyclerview.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun setObserver() {
        viewModel.getZoomPlantListResponse.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    totalItems = it.value.result.limit
                    currItemAmounts += it.value.result.results.size
                    setListData(it.value.result.results)

                    activity.dismissProgressBar()
                }

                is Resource.Failure -> handleApiError(it)

                is Resource.Loading -> activity.showProgressBar(true)
            }
        }
    }

    private fun setListData(results: ArrayList<PlantBean>) {
        updatePlantListData(results)
    }

    private fun updatePlantListData(listPlants: ArrayList<PlantBean>) {
        val tempList = ArrayList<PlantPojo>()

        listPlants.forEach {
            tempList.add(
                PlantPojo(
                    picUrl = it.picUrl,
                    nameCh = it.nameCh ?: it.nameChBug ?: "",
                    nameEn = it.nameEn,
                    nameAlias = it.nameAlias,
                    brief = it.brief,
                    feature = it.feature,
                    application = it.application,
                    lastUpdate =it.lastUpdate
                )
            )
        }

        listAdapter.clearPlantList()
        listAdapter.setPlantList(tempList)
    }

    override fun getViewModel() = HouseInfoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHouseInfoBinding = FragmentHouseInfoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = HouseInfoRepository()
}