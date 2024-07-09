package com.example.mycommish

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycommish.core.di.DatabaseModule
import com.example.mycommish.core.di.RepositoryModule
import com.example.mycommish.feature.prize.data.local.datasource.PrizeObject
import com.example.mycommish.feature.prize.domain.repository.PrizeRepository
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrizeObjectLocalRepositoryTest {

    private lateinit var prizeRepository: PrizeRepository
    private lateinit var databaseModule: Realm

    private var prize1 = PrizeObject().apply {
        name = "Photo Frame"
        value = 3.4
    }

    private var prize2 = PrizeObject().apply {
        name = "Visit Card"
        value = 1.7
    }

    @Before
    fun createDatabase() {
        databaseModule = DatabaseModule.provideRealm()
        prizeRepository = RepositoryModule.provideLocalPrizeRepository(databaseModule)
    }

    @After
    fun closeDatabase() {
        databaseModule.close()
    }

    private suspend fun addOnePrizeToDB() {
        prizeRepository.insertPrize(prize1)
    }

    private suspend fun addTwoPrizesToDB() {
        prizeRepository.insertPrize(prize1)
        prizeRepository.insertPrize(prize2)
    }

    @Test
    @Throws(Exception::class)
    fun prizeInsert_insertsPrizeIntoDB() = runBlocking {
        addOnePrizeToDB()

        val allPrizes = prizeRepository.getAllPrizes().first()
        assertEquals(allPrizes[0].name, prize1.name)
    }

}