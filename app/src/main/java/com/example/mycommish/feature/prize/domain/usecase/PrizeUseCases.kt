package com.example.mycommish.feature.prize.domain.usecase

data class PrizeUseCases(
    val prizeEntryValidatorUseCase: PrizeEntryValidatorUseCase,
    val addPrizeUseCase: AddPrizeUseCase,
    val getPrizes: GetPrizes
)
