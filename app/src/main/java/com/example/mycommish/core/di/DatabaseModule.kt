package com.example.mycommish.core.di

import com.example.mycommish.feature.prize.data.local.datasource.PrizeObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val config = RealmConfiguration.create(
            schema = setOf(PrizeObject::class)
        )
        return Realm.open(config)
    }
}