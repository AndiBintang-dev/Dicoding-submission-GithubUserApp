package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository.DeleteFavoriteUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository.InsertFavoriteUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository.IsFavoriteUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewmodel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf( UserDetailState() )
    val state : State<UserDetailState> = _state

    init {
        savedStateHandle.get<String>("USER_ID")?.let {
           userId -> viewModelScope.launch {
             getUserDetail(userId)
           }
        }
    }

    private fun getUserDetail(userId : String) {
        getUserDetailsUseCase(username = userId).onEach {
            result -> when (result) {
                is Resource.Success -> {
                    _state.value = UserDetailState(user = result.data ?: UserDto() )
                    isFavorite(state.value.user.id!!)
                }
                is Resource.Error -> {
                    _state.value = UserDetailState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertFavorite() {
        insertFavoriteUseCase(user = _state.value.user.toUserListDto() ).onEach {
                result -> when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(isLoading = false, eventStatus = result.data ?: 0)
                        getUserDetail(state.value.user.login!!)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(error = result.message ?: "An unexpected error occured.")
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope)
    }

    private fun isFavorite(userId: Long) {
        isFavoriteUseCase(userId = userId).onEach {
            result -> when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(isLoading = false, isFavorite = result.data ?: false )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}