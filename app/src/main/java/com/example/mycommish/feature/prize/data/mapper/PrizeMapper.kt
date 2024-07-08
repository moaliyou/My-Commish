package com.example.mycommish.feature.prize.data.mapper

import com.example.mycommish.feature.prize.data.local.datasource.PrizeEntity
import com.example.mycommish.feature.prize.domain.model.Prize

//fun PrizeObject.toPrize(): Prize = Prize(
//    id = _id,
//    name = name,
//    value = value.toString(),
//    description = description
//)

//fun Prize.toPrizeObject(): PrizeObject {
//    val prizeRealmObject = PrizeObject()
//    prizeRealmObject._id = id
//    prizeRealmObject.name = name
//    prizeRealmObject.value = value.toDouble()
//    prizeRealmObject.description = description
//    return prizeRealmObject
//}

fun PrizeEntity.toPrize(): Prize = Prize(
    id = id,
    name = name,
    value = value.toString(),
    description = description ?: ""

)

fun Prize.toPrizeEntity(): PrizeEntity = PrizeEntity(
    id = id,
    name = name,
    value = value.toDoubleOrNull() ?: 0.0,
    description = description

)