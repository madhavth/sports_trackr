package com.madhav.sportstrackr.core.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.madhav.sportstrackr.core.di.qualifiers.BaseUrl
import com.madhav.sportstrackr.features.details.data.repositories.TeamRepositoryImpl
import com.madhav.sportstrackr.features.details.data.services.TeamAPI
import com.madhav.sportstrackr.features.details.domain.repositories.TeamRepository
import com.madhav.sportstrackr.features.events.data.repositories.EventRepositoryImpl
import com.madhav.sportstrackr.features.events.data.services.EventAPI
import com.madhav.sportstrackr.features.events.domain.repositories.EventRepository
import com.madhav.sportstrackr.features.search_add.data.repositories.SearchRepositoryImpl
import com.madhav.sportstrackr.features.search_add.data.services.SearchTeamAPI
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// hilt module for retrofit, room
@InstallIn(SingletonComponent::class)
@Module(includes = [RepositoryModule::class])
class AppModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl() = "https://www.thesportsdb.com/api/v1/json/50130162/"

    @Provides
    fun provideRetrofitInstance(@BaseUrl baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}