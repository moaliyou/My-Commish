package com.example.mycommish.feature.prize.data.local.repository

import com.example.mycommish.feature.prize.data.local.datasource.PrizeObject
import com.example.mycommish.feature.prize.domain.repository.PrizeRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class PrizeLocalRepository(
    private val realm: Realm
) : PrizeRepository {
    override fun getAllPrizes(): Flow<List<PrizeObject>> =
        realm.query<PrizeObject>().asFlow().map { it.list }

    override suspend fun insertPrize(prizeObject: PrizeObject) {
        realm.write { copyToRealm(prizeObject) }
    }

    override suspend fun updatePrize(prizeObject: PrizeObject) {
        realm.write {
            val queriedPrize = query<PrizeObject>("_id = $0", prizeObject._id).find().first()

            queriedPrize.name = prizeObject.name
            queriedPrize.value = prizeObject.value
            queriedPrize.description = prizeObject.description
        }
    }

    override suspend fun deletePrize(id: ObjectId) {
        realm.write {

        }
    }
}