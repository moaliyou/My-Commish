package com.example.mycommish.core.di

import android.app.Application
import com.example.mycommish.feature.onboarding.data.repository.LocalUserManagerRepository
import com.example.mycommish.feature.onboarding.domain.repository.UserManagerRepository
import com.example.mycommish.feature.prize.data.MyCommishDatabase
import com.example.mycommish.feature.prize.data.local.repository.PrizeLocalRepository
import com.example.mycommish.feature.prize.data.local.repository.PrizeRepoImpl
import com.example.mycommish.feature.prize.domain.repository.PrizeRepo
import com.example.mycommish.feature.prize.domain.repository.PrizeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalUserManagerRepository(
        application: Application
    ): UserManagerRepository = LocalUserManagerRepository(application)

    @Provides
    @Singleton
    fun provideLocalPrizeRepository(realm: Realm): PrizeRepository = PrizeLocalRepository(realm)

    @Provides
    @Singleton
    fun providesPrizeRepo(myCommishDatabase: MyCommishDatabase): PrizeRepo =
        PrizeRepoImpl(myCommishDatabase.prizeDao)

}