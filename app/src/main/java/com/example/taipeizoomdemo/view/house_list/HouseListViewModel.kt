package com.example.taipeizoomdemo.view.house_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taipeizoomdemo.model.network.Resource
import com.example.taipeizoomdemo.model.network.bean.HouseListResponse
import com.example.taipeizoomdemo.model.repository.HouseListRepository
import com.example.taipeizoomdemo.view.base.BaseViewModel
import kotlinx.coroutines.launch

class HouseListViewModel(private val repository: HouseListRepository): BaseViewModel(repository) {
    private val _getZoomHouseListResponse = MutableLiveData<Resource<HouseListResponse>>()
    val getZoomHouseListResponse: LiveData<Resource<HouseListResponse>>
        get() = _getZoomHouseListResponse

    fun getZoomHouseList() {
        viewModelScope.launch {
            _getZoomHouseListResponse.value = Resource.Loading
            _getZoomHouseListResponse.value = repository.getZoomHouseList()
        }
    }
}