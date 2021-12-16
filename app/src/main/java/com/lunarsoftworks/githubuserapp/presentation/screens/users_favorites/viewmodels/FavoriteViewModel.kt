package com.lunarsoftworks.githubuserapp.presentation.screens.users_favorites.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository.GetAllFavoritesUseCase
import com.lunarsoftworks.githubuserapp.presentation.screens.users_list.viewmodels.UsersListsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : ViewModel() {

    private val _state = mutableStateOf( UsersListsState() )
    val state : State<UsersListsState> = _state

    init {
        viewModelScope.launch {
            getFavorites()
        }
    }

    fun getFavorites() {
        getAllFavoritesUseCase().onEach {
            result -> when (result) {
                is Resource.Success -> {
                    _state.value = UsersListsState(users = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _state.value = UsersListsState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _state.value = UsersListsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}