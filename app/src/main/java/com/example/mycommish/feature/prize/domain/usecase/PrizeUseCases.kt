package com.example.mycommish.feature.prize.domain.usecase

data class PrizeUseCases(
    val prizeEntryValidatorUseCase: PrizeEntryValidatorUseCase,
    val addPrize: AddPrizeUseCase,
    val getPrizes: GetPrizesUseCase,
    val deletePrize: DeletePrizeUseCase,
    val getPrize: GetSinglePrizeUseCase,
    val editPrize: EditPrizeUseCase
)
