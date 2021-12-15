package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels

import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

data class UserFollowingState(
    val isLoading: Boolean = false,
    val following: List<UsersListDto> = emptyList(),
    val error: String = ""
)