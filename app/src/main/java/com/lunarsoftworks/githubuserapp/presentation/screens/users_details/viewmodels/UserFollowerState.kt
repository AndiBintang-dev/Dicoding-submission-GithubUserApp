package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels

import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

data class UserFollowerState(
    val isLoading: Boolean = false,
    val followers: List<UsersListDto> = emptyList(),
    val error: String = ""
)