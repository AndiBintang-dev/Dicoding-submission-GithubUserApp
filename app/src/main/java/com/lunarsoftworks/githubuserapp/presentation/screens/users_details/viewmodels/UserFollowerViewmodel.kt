package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUserFollowersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFollowerViewmodel @Inject constructor(
    private val getUserFollowersUseCase: GetUserFollowersUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf( UserFollowerState() )
    val state : State<UserFollowerState> = _state

    init {
        savedStateHandle.get<String>("USER_ID")?.let {
            userId -> viewModelScope.launch { getUserFollowers(userId) }
        }
    }

    private fun getUserFollowers(userId : String) {
        getUserFollowersUseCase(username = userId).onEach {
                result -> when (result) {
                    is Resource.Success -> {
                        _state.value = UserFollowerState(followers = result.data ?: emptyList() )
                    }
                    is Resource.Error -> {
                        _state.value = UserFollowerState(error = result.message ?: "An unexpected error occured.")
                    }
                    is Resource.Loading -> {
                        _state.value = UserFollowerState(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope)
    }

}