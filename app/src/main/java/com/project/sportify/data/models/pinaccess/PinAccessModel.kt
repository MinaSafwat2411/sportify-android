package com.project.sportify.data.models.pinaccess

import com.project.sportify.R


data class PinAccessModel(
    val value: Int,
    val type: PinType
)


enum class PinType{
    Number, Icon, Empty
}


fun getPinAccessList(): ArrayList<PinAccessModel> {
    return arrayListOf(
        PinAccessModel(1, PinType.Number),
        PinAccessModel(2, PinType.Number),
        PinAccessModel(3, PinType.Number),
        PinAccessModel(4, PinType.Number),
        PinAccessModel(5, PinType.Number),
        PinAccessModel(6, PinType.Number),
        PinAccessModel(7, PinType.Number),
        PinAccessModel(8, PinType.Number),
        PinAccessModel(9, PinType.Number),
        PinAccessModel(-1, PinType.Empty),
        PinAccessModel(0, PinType.Number),
        PinAccessModel(R.drawable.ic_backspace, PinType.Icon),
        )
}