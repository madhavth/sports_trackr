package com.madhav.sportstrackr.core.di.modules

import com.madhav.sportstrackr.core.di.qualifiers.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// hilt module for retrofit, room
@InstallIn(SingletonComponent::class)
@Module()
class AppModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "https://www.thesportsdb.com/api/v1/json/50130162"

    @Provides
    fun provideRetrofitInstance(@BaseUrl baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}