package com.example.mycommish

import com.example.mycommish.core.di.UseCaseModule
import com.example.mycommish.feature.prize.domain.model.Prize
import com.example.mycommish.feature.prize.domain.usecase.PrizeUseCases
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PrizeEntryValidatorUseCaseTest {

    private lateinit var prizeUseCases: PrizeUseCases

    @Before
    fun initialize() {
        prizeUseCases = UseCaseModule.providePrizeUseCases()
    }

    @Test
    fun whenInputIsValid() {
        val prizeEntryData = Prize(name = "Photo Frame", value = "2.5")
        val result = prizeUseCases.prizeEntryValidatorUseCase(prizeEntryData)

        assert(result)
    }

    @Test
    fun whenInputIsNotValid() {
        val prizeEntryData = Prize(name = "", value = "2.5")
        val result = prizeUseCases.prizeEntryValidatorUseCase(prizeEntryData)

        assertEquals(result, false)
    }

}