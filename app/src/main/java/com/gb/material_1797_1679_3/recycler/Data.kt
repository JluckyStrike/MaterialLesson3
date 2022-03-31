package com.gb.material_1797_1679_3.recycler

const val TYPE_DO = 1
const val TYPE_HEADER = 3

data class Data(var name: String = "Text", val description: String?=null, val type:Int=TYPE_DO)

