package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUserFollowersUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUserFollowingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFollowingViewmodel @Inject constructor(
    private val getUserFollowingUseCase: GetUserFollowingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf( UserFollowingState() )
    val state : State<UserFollowingState> = _state

    init {
        savedStateHandle.get<String>("USER_ID")?.let {
            userId -> viewModelScope.launch { getUserFollowing(userId) }
        }
    }

    private fun getUserFollowing(userId : String) {
        getUserFollowingUseCase(username = userId).onEach {
                result -> when (result) {
                    is Resource.Success -> {
                        _state.value = UserFollowingState(following = result.data ?: emptyList() )
                    }
                    is Resource.Error -> {
                        _state.value = UserFollowingState(error = result.message ?: "An unexpected error occured.")
                    }
                    is Resource.Loading -> {
                        _state.value = UserFollowingState(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope)
    }

}