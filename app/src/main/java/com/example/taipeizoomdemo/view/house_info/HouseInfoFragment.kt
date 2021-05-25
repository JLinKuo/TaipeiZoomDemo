package com.example.taipeizoomdemo.view.house_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.taipeizoomdemo.databinding.FragmentHouseInfoBinding
import com.example.taipeizoomdemo.model.repository.HouseInfoRepository
import com.example.taipeizoomdemo.view.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class HouseInfoFragment : BaseFragment<HouseInfoViewModel, FragmentHouseInfoBinding, HouseInfoRepository>() {

    private val args: HouseInfoFragmentArgs by navArgs()
    private val housePojo by lazy { args.HousePojo }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("JLin", "name: ${housePojo.name}")
    }

    override fun getViewModel() = HouseInfoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHouseInfoBinding = FragmentHouseInfoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = HouseInfoRepository()
}