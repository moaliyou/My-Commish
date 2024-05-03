package com.example.mycommish.core.di

import com.example.mycommish.feature.onboarding.domain.repository.UserManagerRepository
import com.example.mycommish.feature.onboarding.domain.use_case.AppEntryUseCase
import com.example.mycommish.feature.onboarding.domain.use_case.ReadAppEntryUseCase
import com.example.mycommish.feature.onboarding.domain.use_case.SaveAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        userManagerRepository: UserManagerRepository
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntryUseCase(userManagerRepository),
        saveAppEntry = SaveAppEntryUseCase(userManagerRepository)
    )

}