package com.example.taipeizoomdemo.view.house_info

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
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
import com.example.taipeizoomdemo.view.base.BaseFragment
import com.example.taipeizoomdemo.view.base.handleApiError
import com.example.taipeizoomdemo.view.pojo.PlantPojo
import com.google.android.material.appbar.AppBarLayout

/**
 * A simple [Fragment] subclass.
 */
class HouseInfoFragment :
        BaseFragment<HouseInfoViewModel, FragmentHouseInfoBinding>(),
        HouseInfoListAdapter.ItemClickListener
{

    private val args: HouseInfoFragmentArgs by navArgs()
    private val housePojo by lazy { args.HousePojo }

    private val listAdapter by lazy { HouseInfoListAdapter(housePojo, this) }
    private var totalItems = 0
    private var currItemAmounts = 0      // 一開始所累積的Item數量是0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHouseListData()
        setView()
        setListener()
        setObserver()
    }

    // HouseInfoListAdapter.ItemClickListener
    override fun onWebLinkClicked(webUrl: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)))
    }

    override fun onPlantSelected(plant: PlantPojo) {
        findNavController().navigate(
            HouseInfoFragmentDirections.actionHouseInfoFragmentToPlantInfoFragment(plant)
        )
    }

    private fun getHouseListData() {
        viewModel.getZoomPlantList()
    }

    private fun setView() {
        setTitle()
        Glide.with(activity).load(housePojo.picUrl).into(binding.image)
        setRecyclerView()
    }

    private fun setTitle() {
        binding.collapsingLayout.title = housePojo.name
        binding.collapsingLayout.setCollapsedTitleTextColor(ContextCompat.getColor(activity, R.color.gray_1))
        binding.collapsingLayout.setCollapsedTitleTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
        binding.collapsingLayout.setExpandedTitleColor(Color.WHITE)
    }

    private fun setRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.adapter = listAdapter
        binding.recyclerview.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun setListener() {
        binding.back.setOnClickListener {
            closeThePage()
        }

        binding.appBarLayout.addOnOffsetChangedListener(
                AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                val isExpanded = (-1 * verticalOffset <= (appBarLayout.totalScrollRange) / 1.5)
                binding.back.setImageDrawable(ContextCompat.getDrawable(activity,
                    if(isExpanded) R.drawable.ic_back_white else R.drawable.ic_back_black)
                )
            }
        )
    }

    private fun closeThePage() {
        findNavController().popBackStack()
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
}