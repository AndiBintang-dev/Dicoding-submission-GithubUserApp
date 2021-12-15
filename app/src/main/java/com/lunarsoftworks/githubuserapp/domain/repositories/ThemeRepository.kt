package com.lunarsoftworks.githubuserapp.domain.repositories

interface ThemeRepository {

    suspend fun getSelectedTheme() : String

    suspend fun setSelectedTheme(themeValue : String) : String

}