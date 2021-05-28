package com.example.taipeizoomdemo.view.house_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taipeizoomdemo.model.network.Resource
import com.example.taipeizoomdemo.model.network.bean.PlantListResponse
import com.example.taipeizoomdemo.model.repository.HouseInfoRepository
import com.example.taipeizoomdemo.view.base.BaseViewModel
import kotlinx.coroutines.launch

class HouseInfoViewModel(private val repository: HouseInfoRepository): BaseViewModel(repository) {
    private val _getZoomPlantListResponse = MutableLiveData<Resource<PlantListResponse>>()
    val getZoomPlantListResponse: LiveData<Resource<PlantListResponse>>
        get() = _getZoomPlantListResponse

    fun getZoomPlantList() {
        viewModelScope.launch {
            _getZoomPlantListResponse.value = Resource.Loading
            _getZoomPlantListResponse.value = repository.getZoomPlantList()
        }
    }
}