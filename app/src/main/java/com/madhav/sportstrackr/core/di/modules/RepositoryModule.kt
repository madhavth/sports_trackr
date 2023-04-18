package com.madhav.sportstrackr.core.di.modules

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

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideEventAPI(retrofit: Retrofit): EventAPI = retrofit.create(EventAPI::class.java)

    @Provides
    fun provideTeamAPI(retrofit: Retrofit): TeamAPI = retrofit.create(TeamAPI::class.java)

    @Provides
    fun provideSearchAPI(retrofit: Retrofit): SearchTeamAPI = retrofit.create(SearchTeamAPI::class.java)

    @Provides
    fun provideEventsRepository(eventRepositoryImpl: EventRepositoryImpl): EventRepository = eventRepositoryImpl

    @Provides
    fun provideTeamRepository(teamRepositoryImpl: TeamRepositoryImpl): TeamRepository = teamRepositoryImpl

    @Provides
    fun provideSearchTeamRepository(searchTeamRepositoryImpl: SearchRepositoryImpl): SearchRepository = searchTeamRepositoryImpl

}