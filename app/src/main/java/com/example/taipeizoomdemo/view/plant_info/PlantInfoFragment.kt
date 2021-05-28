package com.example.taipeizoomdemo.view.plant_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.taipeizoomdemo.databinding.FragmentPlantInfoBinding
import com.example.taipeizoomdemo.model.repository.PlantInfoRepository
import com.example.taipeizoomdemo.view.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class PlantInfoFragment : BaseFragment<PlantInfoViewModel, FragmentPlantInfoBinding, PlantInfoRepository>() {

    private val args: PlantInfoFragmentArgs by navArgs()
    private val plantPojo by lazy { args.plantPojo }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("JLin", "plant name: ${plantPojo.nameCh}")
    }

    override fun getViewModel() = PlantInfoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlantInfoBinding = FragmentPlantInfoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = PlantInfoRepository()
}