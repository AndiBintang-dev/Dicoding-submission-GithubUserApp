package com.lunarsoftworks.githubuserapp.data.local

import android.content.SharedPreferences
import android.util.Log
import com.lunarsoftworks.githubuserapp.common.Constant

class ThemeServiceImpl(private val sharedPreferences: SharedPreferences) : ThemeService {

    init {
        Log.println(Log.DEBUG,"1","ThemeServiceImpl() created.")
    }

    override suspend fun getSelectedTheme(): String {
        Log.println(Log.DEBUG,"1","getSelectedTheme() Fired.")
        if ( !sharedPreferences.all.containsKey(Constant.selectedThemeKey) ) {
            sharedPreferences.edit().putString(Constant.selectedThemeKey, Constant.selectedThemeDefaultValue).apply()
        }
        val themeValue = sharedPreferences.getString(Constant.selectedThemeKey, Constant.selectedThemeDefaultValue)
        Log.println(Log.DEBUG,"1","THEME VALUE = $themeValue")
        return themeValue!!
    }

    override suspend fun setSelectedTheme(themeValue : String) : String {
        sharedPreferences.edit().putString(Constant.selectedThemeKey, themeValue).apply()
        val newThemeValue = sharedPreferences.getString(Constant.selectedThemeKey, Constant.selectedThemeDefaultValue)
        return newThemeValue!!
    }

}