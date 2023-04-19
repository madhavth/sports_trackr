package com.madhav.sportstrackr.core.di.modules

import android.content.Context
import com.madhav.sportstrackr.features.details.data.repositories.TeamRepositoryImpl
import com.madhav.sportstrackr.features.details.data.services.TeamAPI
import com.madhav.sportstrackr.features.details.domain.repositories.TeamRepository
import com.madhav.sportstrackr.features.events.data.repositories.EventRepositoryImpl
import com.madhav.sportstrackr.features.events.data.services.EventAPI
import com.madhav.sportstrackr.features.events.domain.repositories.EventRepository
import com.madhav.sportstrackr.features.favorite.data.repositories.FavoriteRepositoryImpl
import com.madhav.sportstrackr.features.favorite.data.repositories.UserRepository
import com.madhav.sportstrackr.features.favorite.domain.repositories.FavoriteRepository
import com.madhav.sportstrackr.features.search_add.data.repositories.SearchRepositoryImpl
import com.madhav.sportstrackr.features.search_add.data.services.SearchTeamAPI
import com.madhav.sportstrackr.features.search_add.domain.repositories.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideEventAPI(retrofit: Retrofit): EventAPI = retrofit.create(EventAPI::class.java)

    @Provides
    fun provideTeamAPI(retrofit: Retrofit): TeamAPI = retrofit.create(TeamAPI::class.java)

    @Provides
    fun provideSearchAPI(retrofit: Retrofit): SearchTeamAPI =
        retrofit.create(SearchTeamAPI::class.java)

    @Provides
    fun provideEventsRepository(eventRepositoryImpl: EventRepositoryImpl): EventRepository =
        eventRepositoryImpl

    @Provides
    fun provideTeamRepository(teamRepositoryImpl: TeamRepositoryImpl): TeamRepository =
        teamRepositoryImpl

    @Provides
    fun provideSearchTeamRepository(searchTeamRepositoryImpl: SearchRepositoryImpl): SearchRepository =
        searchTeamRepositoryImpl

    @Provides
    fun provideFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository = favoriteRepositoryImpl

    @Singleton
    @Provides
    fun providesUserRepository(@ApplicationContext context: Context)=  UserRepository(context)
}