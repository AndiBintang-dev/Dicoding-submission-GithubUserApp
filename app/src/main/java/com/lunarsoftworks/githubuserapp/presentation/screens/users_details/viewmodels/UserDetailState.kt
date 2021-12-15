package com.lunarsoftworks.githubuserapp.presentation.screens.users_details.viewmodels

import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: UserDto = UserDto(),
    val followers: List<UsersListDto> = emptyList(),
    val following: List<UsersListDto> = emptyList(),
    val isFavorite : Boolean = true,
    val eventStatus : Int = 0,
    val error: String = ""
)
