package com.example.acase.di

import com.example.acase.data.remote.repository.DataRepository
import com.example.acase.data.remote.service.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object ReposityoryModelModule {

    @Provides
    fun providesDataRepo(apiService: Api): DataRepository {
        return DataRepository(apiService)
    }
}