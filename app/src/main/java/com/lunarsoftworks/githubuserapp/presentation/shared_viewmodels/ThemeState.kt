package com.lunarsoftworks.githubuserapp.presentation.shared_viewmodels

data class ThemeState (
    val selectedTheme : String = "",
    val error: String = "",
    val isLoading: Boolean = false,
 )