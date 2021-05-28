package com.example.taipeizoomdemo.model.network.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlantListResponse(
    @SerializedName("result")
    val result: PlantListBean
): Serializable

data class PlantListBean(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("sort")
    val sort: String,
    @SerializedName("results")
    val results: ArrayList<PlantBean>
): Serializable

data class PlantBean(
    @SerializedName("F_Pic01_URL")
    val picUrl: String,
    @SerializedName("F_Name_Ch")      // API本應正常的Key值
    val nameCh: String?,
    @SerializedName("\uFEFFF_Name_Ch")      // API的Bug，多帶了不該有的﻿﻿"\﻿uFEFF"字串，會是隱形狀態
    val nameChBug: String?,
    @SerializedName("F_Name_En")
    val nameEn: String,
    @SerializedName("F_AlsoKnown")
    val nameAlias: String,
    @SerializedName("F_Brief")
    val brief: String,
    @SerializedName("F_Feature")
    val feature: String,
    @SerializedName("F_Function＆Application")
    val application: String,
    @SerializedName("F_Update")
    val lastUpdate: String
): Serializable