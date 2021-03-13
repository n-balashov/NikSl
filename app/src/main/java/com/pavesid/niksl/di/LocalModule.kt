package com.pavesid.niksl.di

import android.content.Context
import androidx.room.Room
import com.pavesid.niksl.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "sqlite.db")
            .createFromAsset("sqlite.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
