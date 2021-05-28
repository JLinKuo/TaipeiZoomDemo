package com.example.taipeizoomdemo.view.plant_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taipeizoomdemo.databinding.FragmentPlantInfoBinding
import com.example.taipeizoomdemo.model.repository.PlantInfoRepository
import com.example.taipeizoomdemo.view.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class PlantInfoFragment : BaseFragment<PlantInfoViewModel, FragmentPlantInfoBinding, PlantInfoRepository>() {

    override fun getViewModel() = PlantInfoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlantInfoBinding = FragmentPlantInfoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = PlantInfoRepository()
}