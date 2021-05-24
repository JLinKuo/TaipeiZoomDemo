package com.example.taipeizoomdemo.view.pojo

import java.io.Serializable

data class HousePojo(
    val picUrl: String,
    val gps: String,
    val info: String,
    val no: String,
    val category: String,
    val name: String,
    val memo: String,
    val _id: String,
    val webUrl: String
): Serializable