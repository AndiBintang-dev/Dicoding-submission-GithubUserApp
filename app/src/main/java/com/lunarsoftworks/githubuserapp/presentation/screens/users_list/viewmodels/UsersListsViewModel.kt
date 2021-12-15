package com.lunarsoftworks.githubuserapp.presentation.screens.users_list.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUsersUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.SearchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListsViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase,
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = mutableStateOf( UsersListsState() )
    val state : State<UsersListsState> = _state

    init {
        viewModelScope.launch {
            getUsersList()
        }
    }

    fun searchUser(username : String) {
        if (username.isEmpty()) {
            return getUsersList()
        }
        searchUserUseCase(username = username).onEach {
          result -> when (result) {
                is Resource.Success -> {
                    _state.value = UsersListsState(searchedUser = result.data )
                }
                is Resource.Error -> {
                    _state.value = UsersListsState(
                        error = result.message ?: "An unexpected error occured."
                    )
                }
                is Resource.Loading -> {
                    _state.value = UsersListsState(isLoading = true)
                }
          }
        }.launchIn(viewModelScope)
    }

    private fun getUsersList() {
        getUsersUseCase().onEach {
            result -> when (result) {
                is Resource.Success -> {
                    _state.value = UsersListsState(users = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _state.value = UsersListsState(
                        error = result.message ?: "An unexpected error occured."
                    )
                }
                is Resource.Loading -> {
                    _state.value = UsersListsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}