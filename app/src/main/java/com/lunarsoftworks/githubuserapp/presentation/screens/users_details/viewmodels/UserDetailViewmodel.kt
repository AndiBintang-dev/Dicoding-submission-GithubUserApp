package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository.DeleteFavoriteUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository.InsertFavoriteUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository.IsFavoriteUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUserDetailsUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUserFollowersUseCase
import com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository.GetUserFollowingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewmodel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserFollowersUseCase: GetUserFollowersUseCase,
    private val getUserFollowingUseCase: GetUserFollowingUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
) : ViewModel() {

    private val _detailState = mutableStateOf( UserDetailState() )
    val detailState : State<UserDetailState> = _detailState

    private val _followerState = mutableStateOf( UserFollowerState() )
    val followerState : State<UserFollowerState> = _followerState

    private val _followingState = mutableStateOf( UserFollowingState() )
    val followingState : State<UserFollowingState> = _followingState

    val eventStatus = MutableLiveData<Int>()

     fun getUserDetail(userId : String) {
        getUserDetailsUseCase(username = userId).onEach {
            result -> when (result) {
                is Resource.Success -> {
                    _detailState.value = UserDetailState(user = result.data ?: UserDto())
                    getUserFollowers(userId = userId)
                    getUserFollowing(userId = userId)
                    _detailState.value.user.id?.let { isFavorite(userId = it) }
                }
                is Resource.Error -> {
                    _detailState.value = UserDetailState(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _detailState.value = UserDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserFollowers(userId : String) {
        getUserFollowersUseCase(username = userId).onEach {
            result -> when (result) {
            is Resource.Success -> {
                _followerState.value = UserFollowerState(followers = result.data ?: emptyList() )
            }
            is Resource.Error -> {
                _followerState.value = UserFollowerState(error = result.message ?: "An unexpected error occured.")
            }
            is Resource.Loading -> {
                _followerState.value = UserFollowerState(isLoading = true)
            }
        }
        }.launchIn(viewModelScope)
    }

    private fun getUserFollowing(userId : String) {
        getUserFollowingUseCase(username = userId).onEach {
            result -> when (result) {
            is Resource.Success -> {
                _followingState.value = UserFollowingState(following = result.data ?: emptyList() )
            }
            is Resource.Error -> {
                _followingState.value = UserFollowingState(error = result.message ?: "An unexpected error occured.")
            }
            is Resource.Loading -> {
                _followingState.value = UserFollowingState(isLoading = true)
            }
        }
        }.launchIn(viewModelScope)
    }

    fun insertFavorite() {
        insertFavoriteUseCase(user = _detailState.value.user.toUserListDto() ).onEach {
                result -> when (result) {
                    is Resource.Success -> {
                        _detailState.value = detailState.value.copy(isLoading = false)
                        isFavorite(detailState.value.user.id!!)
                        eventStatus.postValue(1)
                    }
                    is Resource.Error -> {
                        _detailState.value = detailState.value.copy(error = result.message ?: "An unexpected error occured.")
                    }
                    is Resource.Loading -> {
                        _detailState.value = detailState.value.copy(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope)
    }

    fun deleteFavorite() {
        deleteFavoriteUseCase(user = _detailState.value.user.toUserListDto() ).onEach {
                result -> when (result) {
                    is Resource.Success -> {
                        _detailState.value = detailState.value.copy(isLoading = false, eventStatus = 2)
                        isFavorite(detailState.value.user.id!!)
                        eventStatus.postValue(2)
                    }
                    is Resource.Error -> {
                        _detailState.value = detailState.value.copy(error = result.message ?: "An unexpected error occured.")
                    }
                    is Resource.Loading -> {
                        _detailState.value = detailState.value.copy(isLoading = true)
                    }
                }
        }.launchIn(viewModelScope)
    }


    private fun isFavorite(userId: Long) {
        isFavoriteUseCase(userId = userId).onEach {
            result -> when (result) {
                is Resource.Success -> {
                    _detailState.value = detailState.value.copy(isLoading = false, isFavorite = result.data ?: false )
                }
                is Resource.Error -> {
                    _detailState.value = detailState.value.copy(error = result.message ?: "An unexpected error occured.")
                }
                is Resource.Loading -> {
                    _detailState.value = detailState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}