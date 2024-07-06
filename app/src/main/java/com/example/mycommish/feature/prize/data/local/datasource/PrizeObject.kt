package com.example.mycommish.feature.prize.data.local.datasource

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class PrizeObject : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var value: Double = 0.0
    var description: String = ""
}