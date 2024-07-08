package com.example.mycommish.core.di

import android.content.Context
import androidx.room.Room
import com.example.mycommish.feature.prize.data.local.MyCommishDatabase
import com.example.mycommish.feature.prize.data.local.datasource.PrizeObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRealmDatabase(): Realm {
        val config = RealmConfiguration.create(
            schema = setOf(PrizeObject::class)
        )
        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): MyCommishDatabase =
        Room.databaseBuilder(
            appContext,
            MyCommishDatabase::class.java,
            "mycommish_db"
        ).build()
}