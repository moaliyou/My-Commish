package com.example.mycommish.feature.prize.domain.model

import org.mongodb.kbson.ObjectId

data class Prize(
    val id: ObjectId = ObjectId(),
    val name: String = "",
    val value: String = "",
    val description: String = ""
)