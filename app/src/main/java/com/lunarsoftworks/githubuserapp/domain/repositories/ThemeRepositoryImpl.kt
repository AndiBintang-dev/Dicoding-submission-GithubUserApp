package com.lunarsoftworks.githubuserapp.domain.repositories

import com.lunarsoftworks.githubuserapp.data.local.ThemeService
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val service: ThemeService
) : ThemeRepository {

    override suspend fun getSelectedTheme(): String {
        return service.getSelectedTheme()
    }

    override suspend fun setSelectedTheme(themeValue : String) : String {
        return service.setSelectedTheme(themeValue = themeValue)
    }

}