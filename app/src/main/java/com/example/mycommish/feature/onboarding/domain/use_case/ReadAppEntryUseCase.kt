package com.example.mycommish.feature.onboarding.domain.use_case

import com.example.mycommish.feature.onboarding.domain.repository.UserManagerRepository
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(
    private val userManagerRepository: UserManagerRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return userManagerRepository.readAppEntry()
    }
}