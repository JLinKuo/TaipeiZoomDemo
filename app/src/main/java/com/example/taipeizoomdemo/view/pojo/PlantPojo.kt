package com.example.taipeizoomdemo.view.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlantPojo(
    val picUrl: String,
    val nameCh: String,
    val nameEn: String,
    val nameAlias: String,
    val brief: String,
    val feature: String,
    val application: String,
    val lastUpdate: String
): Serializable