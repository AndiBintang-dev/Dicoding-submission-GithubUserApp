package com.lunarsoftworks.githubuserapp.data.local

import android.content.Context
import android.util.Log
import com.lunarsoftworks.githubuserapp.common.Constant

interface ThemeService {

    suspend fun getSelectedTheme() : String

    suspend fun setSelectedTheme(themeValue : String) : String

    companion object {

        fun create(context: Context) : ThemeService {
            Log.println(Log.DEBUG,"1","ThemeService.create() Fired.")
            return ThemeServiceImpl(
                sharedPreferences = context.getSharedPreferences(
                    Constant.themeSharedPreference,
                    Context.MODE_PRIVATE,
                )
            )
        }

    }

}