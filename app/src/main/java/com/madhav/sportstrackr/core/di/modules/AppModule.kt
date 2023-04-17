package com.madhav.sportstrackr.core.di.modules

import com.madhav.sportstrackr.core.di.qualifiers.BaseUrl
import com.madhav.sportstrackr.features.events.data.repositories.EventRepositoryImpl
import com.madhav.sportstrackr.features.events.data.services.EventAPI
import com.madhav.sportstrackr.features.events.domain.repositories.EventRepository
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
    fun provideBaseUrl() = "https://www.thesportsdb.com/api/v1/json/50130162/"

    @Provides
    fun provideRetrofitInstance(@BaseUrl baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideEventAPI(retrofit: Retrofit): EventAPI = retrofit.create(EventAPI::class.java)

    @Provides
    fun provideEventsRepository(eventRepositoryImpl: EventRepositoryImpl): EventRepository = eventRepositoryImpl
}