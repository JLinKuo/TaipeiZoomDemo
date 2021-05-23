package com.example.taipeizoomdemo.model.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HouseListResponse(
    @SerializedName("result")
    val result: HouseListBean
): Serializable

data class HouseListBean(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("sort")
    val sort: String,
    @SerializedName("results")
    val results: ArrayList<HouseBean>
): Serializable

data class HouseBean(
    @SerializedName("E_Pic_URL")
    val picUrl: String,
    @SerializedName("E_Geo")
    val gps: String,
    @SerializedName("E_Info")
    val info: String,
    @SerializedName("E_no")
    val no: String,
    @SerializedName("E_Category")
    val category: String,
    @SerializedName("E_Name")
    val name: String,
    @SerializedName("E_Memo")
    val memo: String,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("E_URL")
    val webUrl: String
): Serializable