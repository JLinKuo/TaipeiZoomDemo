package com.example.taipeizoomdemo.view.house_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoomdemo.R
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
class HouseListFragment : BaseFragment<HouseListViewModel, FragmentHouseListBinding>() {

    private val listAdapter by lazy { HouseListItemAdapter( object: HouseListItemAdapter.SelectItemListener {
        override fun onItemSelected(housePojo: HousePojo) {
            findNavController().navigate(
                HouseListFragmentDirections.actionHouseListFragmentToHouseInfoFragment(housePojo)
            )
        }
    }) }
    private var totalItems = 0
    private var currItemAmounts = 0      // 一開始所累積的Item數量是0

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
        setToggle()
        setRecyclerView()
    }

    private fun setToggle() {
        val toggle = ActionBarDrawerToggle(activity, binding.drawerLayout, binding.toolbarLayout,
            R.string.drawer_open, R.string.drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = listAdapter
        binding.recyclerview.addItemDecoration(DividerItemDecoration(activity, VERTICAL))
//        binding.recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//
//            private var lastVisibleItemPosition = 0
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if(newState == RecyclerView.SCROLL_STATE_IDLE &&
//                        lastVisibleItemPosition + 1 == listAdapter.itemCount && currItemAmounts < totalItems) {
//                    getHouseListData()
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
//            }
//        })
    }

    private fun setObserver() {
        viewModel.getZoomHouseListResponse.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Success -> {
                    totalItems = it.value.result.limit
                    currItemAmounts += it.value.result.results.size
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
}