package com.pavesid.niksl.di

import com.pavesid.niksl.data.DataRepository
import com.pavesid.niksl.data.DataRepositoryImpl
import com.pavesid.niksl.data.local.LocalSource
import com.pavesid.niksl.data.local.LocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindDataRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository

    @Binds
    abstract fun bindLocalSource(localSourceImpl: LocalSourceImpl): LocalSource
}
