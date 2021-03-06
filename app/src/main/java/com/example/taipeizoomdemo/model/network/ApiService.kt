package com.example.taipeizoomdemo.model.network

import com.example.taipeizoomdemo.model.network.bean.HouseListResponse
import com.example.taipeizoomdemo.model.network.bean.PlantListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getZoomHouseList(): HouseListResponse

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    suspend fun getZoomPlantList(): PlantListResponse
}