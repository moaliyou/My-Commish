package com.example.mycommish.core.di

import com.example.mycommish.feature.prize.data.local.MyCommishDatabase
import com.example.mycommish.feature.prize.domain.repository.PrizeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePrizeDao(myCommishDatabase: MyCommishDatabase) : PrizeDao{
        return myCommishDatabase.prizeDao()
    }
}