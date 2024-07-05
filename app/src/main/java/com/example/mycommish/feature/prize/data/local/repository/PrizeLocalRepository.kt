package com.example.mycommish.feature.prize.data.local.repository

import com.example.mycommish.feature.prize.data.local.datasource.Prize
import com.example.mycommish.feature.prize.domain.repository.PrizeRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class PrizeLocalRepository(
    private val realm: Realm
) : PrizeRepository {
    override fun getAllPrizes(): Flow<List<Prize>> = realm.query<Prize>().asFlow().map { it.list }

    override suspend fun insertPrize(prize: Prize) {
        realm.write { copyToRealm(prize) }
    }

    override suspend fun updatePrize(prize: Prize) {
        realm.write {
            val queriedPrize = query<Prize>("_id = $0", prize._id).find().first()

            queriedPrize.name = prize.name
            queriedPrize.value = prize.value
            queriedPrize.description = prize.description
        }
    }

    override suspend fun deletePrize(id: ObjectId) {
        realm.write {

        }
    }
}