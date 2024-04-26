package com.example.mycommish.feature.onboarding.domain.use_case

import com.example.mycommish.feature.onboarding.domain.repository.UserManagerRepository

class SaveAppEntryUseCase(
    private val userManagerRepository: UserManagerRepository
) {
    suspend fun invoke() {
        userManagerRepository.saveAppEntry()
    }
}