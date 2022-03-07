package com.gb.material_1797_1679_3.repository


import com.google.gson.annotations.SerializedName

data class Rover(
    val id: Int,
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launchDate: String,
    val name: String,
    val status: String
)