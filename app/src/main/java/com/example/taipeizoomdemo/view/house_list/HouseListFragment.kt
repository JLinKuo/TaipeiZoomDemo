package com.example.taipeizoomdemo.view.house_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoomdemo.databinding.FragmentHouseListBinding
import com.example.taipeizoomdemo.model.network.Resource
import com.example.taipeizoomdemo.model.network.bean.HouseBean
import com.example.taipeizoomdemo.model.repository.HouseListRepository
import com.example.taipeizoomdemo.view.base.BaseFragment
import com.example.taipeizoomdemo.view.base.handleApiError
import com.example.taipeizoomdemo.view.pojo.HousePojo

/**
 * A simple [Fragment] subclass.
 */
class HouseListFragment : BaseFragment<HouseListViewModel, FragmentHouseListBinding, HouseListRepository>() {

    private val listAdapter by lazy { HouseListItemAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHouseListData()
        setView()
        setObserver()
    }

    private fun getHouseListData() {
        viewModel.getZoomHouseList()
    }

    private fun setView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.adapter = listAdapter
        binding.recyclerview.addItemDecoration(DividerItemDecoration(activity, VERTICAL))
    }

    private fun setObserver() {
        viewModel.getZoomHouseListResponse.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    updateHouseListData(it.value.result.results)
                    activity.dismissProgressBar()
                }

                is Resource.Failure -> handleApiError(it)

                is Resource.Loading -> activity.showProgressBar(true)
            }
        }
    }

    private fun updateHouseListData(listHouses: ArrayList<HouseBean>) {
        val tempList = ArrayList<HousePojo>()

        listHouses.forEach {
            tempList.add(
                HousePojo(
                    picUrl = it.picUrl,
                    gps = it.gps,
                    info = it.info,
                    no = it.no,
                    category = it.category,
                    name = it.name,
                    memo = it.memo,
                    _id = it._id,
                    webUrl = it.webUrl
                )
            )
        }

        listAdapter.clearList()
        listAdapter.updateList(tempList)
    }

    override fun getViewModel() = HouseListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHouseListBinding = FragmentHouseListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = HouseListRepository()
}