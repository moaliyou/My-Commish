package com.example.mycommish.feature.onboarding.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserManagerRepository {
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}