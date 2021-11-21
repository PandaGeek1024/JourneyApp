package com.example.journeyapp.presentation.di

import com.example.journeyapp.data.api.JourneyApi
import com.example.journeyapp.data.api.JourneyApiService
import com.example.journeyapp.data.repository.PostDataSource
import com.example.journeyapp.data.repository.PostDataSourceImpl
import com.example.journeyapp.data.repository.PostRepositoryImpl
import com.example.journeyapp.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {
    @Singleton
    @Provides
    fun provideJourneyApiService(): JourneyApiService {
        return JourneyApi.journeyApiService
    }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class ApplicationBindModule {
    @Singleton
    @Binds
    abstract fun bindPostDataSource(impl: PostDataSourceImpl): PostDataSource

    @Singleton
    @Binds
    abstract fun bindPostRepository(impl: PostRepositoryImpl): PostRepository
}