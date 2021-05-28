package com.example.taipeizoomdemo.model.repository

class HouseInfoRepository: BaseRepository() {
    suspend fun getZoomPlantList() = safeApiCall {
        apiService.getZoomPlantList()
    }
}