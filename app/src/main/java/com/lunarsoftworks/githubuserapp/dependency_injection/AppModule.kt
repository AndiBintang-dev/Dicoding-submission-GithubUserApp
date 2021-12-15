package com.lunarsoftworks.githubuserapp.dependency_injection

import android.content.Context
import com.lunarsoftworks.githubuserapp.data.local.ThemeService
import com.lunarsoftworks.githubuserapp.data.remote.GithubService
import com.lunarsoftworks.githubuserapp.domain.repositories.GithubRepository
import com.lunarsoftworks.githubuserapp.domain.repositories.GithubRepositoryImpl
import com.lunarsoftworks.githubuserapp.domain.repositories.ThemeRepository
import com.lunarsoftworks.githubuserapp.domain.repositories.ThemeRepositoryImpl
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

}