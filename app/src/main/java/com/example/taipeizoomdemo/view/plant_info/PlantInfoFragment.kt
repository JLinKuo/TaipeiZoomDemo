package com.example.taipeizoomdemo.view.plant_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.taipeizoomdemo.R
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

        setView()
        setListener()
    }

    private fun setView() {
        binding.title.text = plantPojo.nameCh
        binding.nameCh.text = plantPojo.nameCh
        binding.nameEn.text = plantPojo.nameEn
        binding.alias.text = plantPojo.nameAlias
        binding.brief.text = plantPojo.brief
        binding.feature.text = plantPojo.feature
        binding.application.text = plantPojo.application
        binding.lastUpdate.text = String.format(
            getString(R.string.plant_info_last_update), plantPojo.lastUpdate)

        Glide.with(activity).load(plantPojo.picUrl).into(binding.image)
    }

    private fun setListener() {
        binding.back.setOnClickListener {
            closeThePage()
        }
    }

    private fun closeThePage() {
        findNavController().popBackStack()
    }

    override fun getViewModel() = PlantInfoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlantInfoBinding = FragmentPlantInfoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = PlantInfoRepository()
}