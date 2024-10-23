package com.example.nit3213assessment2

import com.example.nit3213assessment2.network.ApiService
import com.example.nit3213assessment2.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApiService(): ApiService {
        return RetrofitClient().apiService
    }
}