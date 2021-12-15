package com.lunarsoftworks.githubuserapp.presentation.screens.users_list.viewmodels

import com.lunarsoftworks.githubuserapp.data.dto.SearchUserDto
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto

data class UsersListsState(
    val isLoading: Boolean = false,
    val users: List<UsersListDto> = emptyList(),
    val error: String = ""
)
