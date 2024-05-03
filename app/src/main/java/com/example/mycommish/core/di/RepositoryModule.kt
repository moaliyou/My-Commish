package com.example.mycommish.core.di

import android.app.Application
import com.example.mycommish.feature.onboarding.data.repository.LocalUserManagerRepository
import com.example.mycommish.feature.onboarding.domain.repository.UserManagerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalUserManagerRepository(
        application: Application
    ): UserManagerRepository = LocalUserManagerRepository(application)

}