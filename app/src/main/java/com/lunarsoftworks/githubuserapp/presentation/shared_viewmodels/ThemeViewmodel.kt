package com.lunarsoftworks.githubuserapp.presentation.shared_viewmodels

import android.util.Log
import androidx.compose.material.Colors
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunarsoftworks.githubuserapp.common.Constant
import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.domain.use_cases.theme_repository.GetSelectedThemeUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.theme_repository.SetSelectedThemeUseCase
import com.lunarsoftworks.githubuserapp.presentation.theme.DarkColorPalette
import com.lunarsoftworks.githubuserapp.presentation.theme.LightColorPalette
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewmodel @Inject constructor(
    private val getSelectedThemeUseCase: GetSelectedThemeUseCase,
    private val setSelectedThemeUseCase: SetSelectedThemeUseCase,
) : ViewModel() {

    private val _state = mutableStateOf( ThemeState() )
    val state : State<ThemeState> = _state

    var status = MutableLiveData<Boolean?>(false)

    init {
        viewModelScope.launch {
            getSelectedTheme()
        }
    }

    private fun getSelectedTheme() {
        getSelectedThemeUseCase().onEach {
            result -> when (result) {
                 is Resource.Success -> {
                     _state.value = ThemeState(selectedTheme = result.data.toString() )
                 }
                 is Resource.Error -> {
                     _state.value = ThemeState(error = result.message ?: "An unexpected error occured.")
                 }
                 is Resource.Loading -> {
                     _state.value = ThemeState(isLoading = true)
                 }
            }
        }.launchIn(viewModelScope)
        Log.println(Log.DEBUG,"1","THEME STATE : ${_state.value}")
    }

    fun setSelectedTheme(themeValue : String) {
        setSelectedThemeUseCase(themeValue = themeValue).onEach {
            result -> when (result) {
                is Resource.Success -> {
                    _state.value = ThemeState(selectedTheme = result.data.toString() )
                    status.value = false
                }
                is Resource.Error -> {
                    _state.value = ThemeState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _state.value = ThemeState(isLoading = true)
                    status.value = true
                }
            }
        }.launchIn(viewModelScope)
        Log.println(Log.DEBUG,"1","THEME STATE : ${_state.value}")
    }

}