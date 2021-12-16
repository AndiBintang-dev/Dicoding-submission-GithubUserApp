package com.lunarsoftworks.githubuserapp.dependency_injection

import android.content.Context
import androidx.room.Room
import com.lunarsoftworks.githubuserapp.common.Constant
import com.lunarsoftworks.githubuserapp.data.databases.AppDatabase
import com.lunarsoftworks.githubuserapp.data.local.FavoriteUsersService
import com.lunarsoftworks.githubuserapp.data.local.ThemeService
import com.lunarsoftworks.githubuserapp.data.remote.GithubService
import com.lunarsoftworks.githubuserapp.domain.repositories.*
import dagger.hilt.components.SingletonComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubService() : GithubService {
        return GithubService.create()
    }

    @Provides
    @Singleton
    fun provideGithubRepository(service: GithubService) : GithubRepository {
        return GithubRepositoryImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideThemeService(@ApplicationContext context: Context) : ThemeService {
        return ThemeService.create(context)
    }

    @Provides
    @Singleton
    fun provideThemeRepository(service: ThemeService) : ThemeRepository {
        return ThemeRepositoryImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideFavoriteUsersService(@ApplicationContext context: Context) : FavoriteUsersService {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, Constant.favoriteUserTableName
        ).allowMainThreadQueries().build().favoriteUsersDao
    }

    @Provides
    @Singleton
    fun provideFavoriteUserRepository(service: FavoriteUsersService) : FavoriteUserRepository {
        return  FavoriteUserRepositoryImpl(service)
    }

}