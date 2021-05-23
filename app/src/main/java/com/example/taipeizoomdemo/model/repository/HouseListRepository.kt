package com.example.taipeizoomdemo.model.repository

class HouseListRepository: BaseRepository() {
    suspend fun getZoomHouseList() = safeApiCall {
        apiService.getZoomHouseList()
    }
}